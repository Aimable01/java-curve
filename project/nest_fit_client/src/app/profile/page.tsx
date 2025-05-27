"use client";

import { useAuth } from "@/context/AuthContext";
import { usePosts } from "@/context/PostContext";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import Link from "next/link";
import { PencilIcon, TrashIcon } from "@heroicons/react/24/outline";
import { format } from "date-fns";

export default function Profile() {
  const { user, logout, isLoading: authLoading, isInitialized } = useAuth();
  const { posts, isLoading: postsLoading, fetchPosts, deletePost } = usePosts();
  const router = useRouter();
  const [isDeleting, setIsDeleting] = useState<number | null>(null);

  useEffect(() => {
    // Wait for auth to initialize before making decisions
    if (!isInitialized) return;

    if (!user) {
      router.push("/login");
      return;
    }

    // Only fetch posts if user is authenticated and initialized
    fetchPosts();
  }, [user, isInitialized, fetchPosts, router]);

  const handleDelete = async (id: number) => {
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

  // Redirect if not authenticated (this will happen after initialization)
  if (!user) {
    return null;
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-white shadow">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16">
            <div className="flex items-center">
              <Link href="/" className="text-2xl font-bold text-purple-600">
                Nest Fit
              </Link>
            </div>
            <div className="flex items-center space-x-4">
              <Link
                href="/create"
                className="bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700"
              >
                Create Post
              </Link>
              <button
                onClick={logout}
                className="text-gray-600 hover:text-purple-600"
              >
                Logout
              </button>
            </div>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="bg-white shadow rounded-lg p-6 mb-8">
            <h2 className="text-2xl font-semibold mb-6">Your Profile</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Username
                </label>
                <div className="mt-1 text-gray-900">{user.username}</div>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Full Name
                </label>
                <div className="mt-1 text-gray-900">{user.fullName}</div>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">
                  Email
                </label>
                <div className="mt-1 text-gray-900">{user.email}</div>
              </div>
            </div>
          </div>

          <div className="bg-white shadow rounded-lg p-6">
            <h2 className="text-2xl font-semibold mb-6">Your Posts</h2>
            {postsLoading ? (
              <div className="text-center py-8">
                <div className="text-gray-600">Loading posts...</div>
              </div>
            ) : (
              <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {posts.map((post) => (
                  <div
                    key={post.id}
                    className="bg-gray-50 rounded-lg p-6 border border-gray-200"
                  >
                    <div className="flex justify-between items-start mb-4">
                      <h3 className="text-xl font-semibold text-gray-900">
                        {post.title}
                      </h3>
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
                    </div>
                    <p className="text-gray-600 mb-4">{post.content}</p>
                    <div className="text-sm text-gray-500">
                      Posted on{" "}
                      {format(new Date(post.createdAt), "MMM d, yyyy")}
                    </div>
                  </div>
                ))}
              </div>
            )}
            {!postsLoading && posts.length === 0 && (
              <div className="text-center py-12">
                <h3 className="text-lg font-medium text-gray-900">
                  No posts yet
                </h3>
                <p className="mt-2 text-gray-600">
                  Start sharing your fitness journey!
                </p>
                <Link
                  href="/create"
                  className="mt-4 inline-block bg-purple-600 text-white px-4 py-2 rounded-lg hover:bg-purple-700"
                >
                  Create Your First Post
                </Link>
              </div>
            )}
          </div>
        </div>
      </main>
    </div>
  );
}
