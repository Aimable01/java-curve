"use client";

import { useEffect, useState } from "react";
import { usePosts } from "@/context/PostContext";
import { useAuth } from "@/context/AuthContext";
import Link from "next/link";
import { PencilIcon, TrashIcon } from "@heroicons/react/24/outline";
import { format } from "date-fns";
import { toast } from "react-hot-toast";

export default function Home() {
  const { posts, isLoading, fetchPosts, deletePost } = usePosts();
  const { user, isLoading: authLoading, isInitialized } = useAuth();
  const [isDeleting, setIsDeleting] = useState<number | null>(null);

  useEffect(() => {
    // Wait for auth context to initialize
    if (!isInitialized) return;

    // Fetch posts regardless of user state for home page
    fetchPosts();
  }, [isInitialized, fetchPosts]);

  const handleDelete = async (id: number) => {
    if (!user) {
      toast.error("Please log in to delete posts");
      return;
    }

    setIsDeleting(id);
    try {
      await deletePost(id);
    } finally {
      setIsDeleting(null);
    }
  };

  // Show loading while auth is initializing
  if (!isInitialized || authLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-xl text-gray-600">Loading...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-white shadow">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16">
            <div className="flex items-center">
              <h1 className="text-2xl font-bold text-purple-600">Nest Fit</h1>
            </div>
            <div className="flex items-center space-x-4">
              {user ? (
                <>
                  <Link
                    href="/profile"
                    className="text-gray-600 hover:text-purple-600"
                  >
                    Profile
                  </Link>
                  <Link
                    href="/create"
                    className="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700"
                  >
                    Create Post
                  </Link>
                </>
              ) : (
                <>
                  <Link
                    href="/login"
                    className="text-gray-600 hover:text-purple-600"
                  >
                    Login
                  </Link>
                  <Link
                    href="/register"
                    className="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700"
                  >
                    Sign Up
                  </Link>
                </>
              )}
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          {!user && (
            <div className="mb-8 bg-purple-50 border border-purple-200 rounded-lg p-4">
              <p className="text-purple-800">
                Please log in to view and interact with posts.
              </p>
            </div>
          )}

          {isLoading ? (
            <div className="text-center py-12">
              <div className="text-xl text-gray-600">Loading posts...</div>
            </div>
          ) : (
            <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
              {posts.map((post) => (
                <div
                  key={post.id}
                  className="bg-white rounded-lg shadow-sm hover:shadow-md transition-shadow p-6"
                >
                  <div className="flex justify-between items-start mb-4">
                    <h2 className="text-xl font-semibold text-gray-900">
                      {post.title}
                    </h2>
                    {user && (
                      <div className="flex space-x-2">
                        <Link
                          href={`/edit/${post.id}`}
                          className="text-gray-400 hover:text-purple-600"
                        >
                          <PencilIcon className="w-5 h-5" />
                        </Link>
                        <button
                          onClick={() => handleDelete(post.id)}
                          disabled={isDeleting === post.id}
                          className="text-gray-400 hover:text-red-600 disabled:opacity-50"
                        >
                          <TrashIcon className="w-5 h-5" />
                        </button>
                      </div>
                    )}
                  </div>
                  <p className="text-gray-600 mb-4 line-clamp-3">
                    {post.content}
                  </p>
                  <div className="text-sm text-gray-500">
                    Posted on {format(new Date(post.createdAt), "MMM d, yyyy")}
                  </div>
                </div>
              ))}
            </div>
          )}

          {!isLoading && posts.length === 0 && (
            <div className="text-center py-12">
              <h3 className="text-lg font-medium text-gray-900">
                {user ? "No posts yet" : "No posts available"}
              </h3>
              <p className="mt-2 text-gray-600">
                {user
                  ? "Be the first to share your fitness journey!"
                  : "Log in to view and create posts."}
              </p>
            </div>
          )}
        </div>
      </main>
    </div>
  );
}
