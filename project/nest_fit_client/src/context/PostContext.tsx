"use client";

import {
  createContext,
  useContext,
  useState,
  ReactNode,
  useEffect,
  useCallback,
} from "react";
import axios from "axios";
import { useAuth } from "./AuthContext";
import toast from "react-hot-toast";

interface Post {
  id: number;
  title: string;
  content: string;
  createdAt: string;
  updatedAt: string;
}

interface PostContextType {
  posts: Post[];
  currentPost: Post | null;
  isLoading: boolean;
  fetchPosts: () => Promise<void>;
  fetchPost: (id: number) => Promise<void>;
  createPost: (title: string, content: string) => Promise<void>;
  updatePost: (id: number, title: string, content: string) => Promise<void>;
  deletePost: (id: number) => Promise<void>;
  setCurrentPost: (post: Post | null) => void;
}

const PostContext = createContext<PostContextType | undefined>(undefined);

export function PostProvider({ children }: { children: ReactNode }) {
  const [posts, setPosts] = useState<Post[]>([]);
  const [currentPost, setCurrentPost] = useState<Post | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const { token, user, isInitialized, logout } = useAuth();

  // Create axios instance with interceptors
  const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
  });

  // Helper function to get auth headers
  const getAuthHeaders = () => {
    const authToken = token || localStorage.getItem("token");
    return authToken ? { Authorization: `Bearer ${authToken}` } : {};
  };

  // Set up axios interceptors
  useEffect(() => {
    const requestInterceptor = axiosInstance.interceptors.request.use(
      (config) => {
        const authToken = token || localStorage.getItem("token");
        if (authToken) {
          config.headers.Authorization = `Bearer ${authToken}`;
        }
        return config;
      },
      (error) => Promise.reject(error)
    );

    const responseInterceptor = axiosInstance.interceptors.response.use(
      (response) => response,
      (error) => {
        if (error.response?.status === 401 || error.response?.status === 403) {
          // Token is invalid, logout user
          logout();
          toast.error("Session expired. Please log in again.");
        }
        return Promise.reject(error);
      }
    );

    return () => {
      axiosInstance.interceptors.request.eject(requestInterceptor);
      axiosInstance.interceptors.response.eject(responseInterceptor);
    };
  }, [token, logout]);

  const fetchPosts = useCallback(async () => {
    const authToken = token || localStorage.getItem("token");
    if (!authToken) {
      setPosts([]);
      return;
    }

    setIsLoading(true);
    try {
      const response = await axiosInstance.get("/notes", {
        headers: getAuthHeaders(),
      });
      setPosts(response.data.data || []);
    } catch (error) {
      console.error("Error fetching posts:", error);
      if (!axios.isAxiosError(error) || error.response?.status !== 401) {
        toast.error("Failed to fetch posts");
      }
      setPosts([]);
    } finally {
      setIsLoading(false);
    }
  }, [token]);

  const fetchPost = useCallback(
    async (id: number) => {
      const authToken = token || localStorage.getItem("token");
      if (!authToken) {
        setCurrentPost(null);
        return;
      }

      setIsLoading(true);
      try {
        const response = await axiosInstance.get(`/notes/${id}`, {
          headers: getAuthHeaders(),
        });
        setCurrentPost(response.data.data);
      } catch (error) {
        console.error("Error fetching post:", error);
        if (!axios.isAxiosError(error) || error.response?.status !== 401) {
          toast.error("Failed to fetch post");
        }
        setCurrentPost(null);
      } finally {
        setIsLoading(false);
      }
    },
    [token]
  );

  const createPost = async (title: string, content: string) => {
    const authToken = token || localStorage.getItem("token");
    if (!authToken) {
      toast.error("Please log in to create posts");
      return;
    }

    setIsLoading(true);
    try {
      const response = await axiosInstance.post(
        "/notes",
        { title, content },
        {
          headers: getAuthHeaders(),
        }
      );
      setPosts((prev) => [response.data.data, ...prev]);
      toast.success("Post created successfully");
    } catch (error) {
      console.error("Error creating post:", error);
      if (!axios.isAxiosError(error) || error.response?.status !== 401) {
        toast.error("Failed to create post");
      }
    } finally {
      setIsLoading(false);
    }
  };

  const updatePost = async (id: number, title: string, content: string) => {
    const authToken = token || localStorage.getItem("token");
    if (!authToken) {
      toast.error("Please log in to update posts");
      return;
    }

    setIsLoading(true);
    try {
      const response = await axiosInstance.put(
        `/notes/${id}`,
        { title, content },
        {
          headers: getAuthHeaders(),
        }
      );
      setPosts((prev) =>
        prev.map((post) => (post.id === id ? response.data.data : post))
      );
      setCurrentPost(response.data.data);
      toast.success("Post updated successfully");
    } catch (error) {
      console.error("Error updating post:", error);
      if (!axios.isAxiosError(error) || error.response?.status !== 401) {
        toast.error("Failed to update post");
      }
    } finally {
      setIsLoading(false);
    }
  };

  const deletePost = async (id: number) => {
    const authToken = token || localStorage.getItem("token");
    if (!authToken) {
      toast.error("Please log in to delete posts");
      return;
    }

    setIsLoading(true);
    try {
      await axiosInstance.delete(`/notes/${id}`, {
        headers: getAuthHeaders(),
      });
      setPosts((prev) => prev.filter((post) => post.id !== id));
      if (currentPost?.id === id) {
        setCurrentPost(null);
      }
      toast.success("Post deleted successfully");
    } catch (error) {
      console.error("Error deleting post:", error);
      if (!axios.isAxiosError(error) || error.response?.status !== 401) {
        toast.error("Failed to delete post");
      }
    } finally {
      setIsLoading(false);
    }
  };

  // Fetch posts when user logs in or context initializes
  useEffect(() => {
    if (isInitialized && (token || localStorage.getItem("token"))) {
      fetchPosts();
    } else if (isInitialized && !token && !localStorage.getItem("token")) {
      setPosts([]);
      setCurrentPost(null);
    }
  }, [token, isInitialized, fetchPosts]);

  return (
    <PostContext.Provider
      value={{
        posts,
        currentPost,
        isLoading,
        fetchPosts,
        fetchPost,
        createPost,
        updatePost,
        deletePost,
        setCurrentPost,
      }}
    >
      {children}
    </PostContext.Provider>
  );
}

export function usePosts() {
  const context = useContext(PostContext);
  if (context === undefined) {
    throw new Error("usePosts must be used within a PostProvider");
  }
  return context;
}
