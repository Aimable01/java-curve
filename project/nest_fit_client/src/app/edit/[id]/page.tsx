"use client";

import { useAuth } from "@/context/AuthContext";
import { usePosts } from "@/context/PostContext";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import PostForm from "@/components/PostForm";

export default function EditPost({ params }: { params: { id: string } }) {
  const { user, isLoading: authLoading } = useAuth();
  const { currentPost, fetchPost, isLoading: postLoading } = usePosts();
  const router = useRouter();
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!authLoading && !user) {
      router.push("/login");
      return;
    }

    const loadPost = async () => {
      try {
        await fetchPost(parseInt(params.id));
      } catch (error) {
        setError("Failed to load post");
        console.error("Error loading post:", error);
      }
    };

    loadPost();
  }, [user, authLoading, router, fetchPost, params.id]);

  if (authLoading || postLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-xl text-gray-600">Loading...</div>
      </div>
    );
  }

  if (!user) {
    return null;
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-xl text-red-600">{error}</div>
      </div>
    );
  }

  if (!currentPost) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-xl text-gray-600">Post not found</div>
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
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="bg-white shadow rounded-lg p-6">
            <h2 className="text-2xl font-semibold mb-6">Edit Post</h2>
            <PostForm
              initialData={{
                id: currentPost.id,
                title: currentPost.title,
                content: currentPost.content,
              }}
            />
          </div>
        </div>
      </main>
    </div>
  );
}
