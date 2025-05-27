"use client";

import {
  createContext,
  useContext,
  useState,
  useEffect,
  ReactNode,
} from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import toast from "react-hot-toast";

interface User {
  id: number;
  username: string;
  fullName: string;
  email: string;
}

interface AuthContextType {
  user: User | null;
  token: string | null;
  login: (username: string, password: string) => Promise<void>;
  register: (
    username: string,
    password: string,
    fullName: string,
    email: string
  ) => Promise<void>;
  logout: () => void;
  isLoading: boolean;
  isInitialized: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [isInitialized, setIsInitialized] = useState(false);
  const router = useRouter();

  // Initialize auth state from localStorage on mount
  useEffect(() => {
    const initializeAuth = () => {
      try {
        const storedToken = localStorage.getItem("token");
        const storedUser = localStorage.getItem("user");

        if (storedToken && storedUser) {
          const parsedUser = JSON.parse(storedUser);
          setToken(storedToken);
          setUser(parsedUser);
        }
      } catch (error) {
        console.error("Error initializing auth:", error);
        // Clear corrupted data
        localStorage.removeItem("token");
        localStorage.removeItem("user");
      } finally {
        setIsInitialized(true);
      }
    };

    initializeAuth();
  }, []);

  const login = async (username: string, password: string) => {
    setIsLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username,
        password,
      });

      const { token: newToken, user: newUser } = response.data.data;

      // Update state first
      setToken(newToken);
      setUser(newUser);

      // Then update localStorage
      localStorage.setItem("token", newToken);
      localStorage.setItem("user", JSON.stringify(newUser));

      toast.success("Login successful!");
      router.push("/dashboard");
    } catch (error) {
      console.error("Login error:", error);
      toast.error("Login failed. Please check your credentials.");
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  const register = async (
    username: string,
    password: string,
    fullName: string,
    email: string
  ) => {
    setIsLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/auth/register", {
        username,
        password,
        fullName,
        email,
      });

      const { token: newToken, user: newUser } = response.data.data;

      // Update state first
      setToken(newToken);
      setUser(newUser);

      // Then update localStorage
      localStorage.setItem("token", newToken);
      localStorage.setItem("user", JSON.stringify(newUser));

      toast.success("Registration successful!");
      router.push("/dashboard");
    } catch (error) {
      console.error("Registration error:", error);
      toast.error("Registration failed. Please try again.");
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  const logout = () => {
    setToken(null);
    setUser(null);
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    router.push("/");
    toast.success("Logged out successfully!");
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        login,
        register,
        logout,
        isLoading,
        isInitialized,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
}
