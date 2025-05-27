"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { usePosts } from "@/context/PostContext";
import Link from "next/link";

interface PostFormProps {
  initialData?: {
    id: number;
    title: string;
    content: string;
  };
}

export default function PostForm({ initialData }: PostFormProps) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const { createPost, updatePost } = usePosts();
  const router = useRouter();

  useEffect(() => {
    if (initialData) {
      setTitle(initialData.title);
      setContent(initialData.content);
    }
  }, [initialData]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      if (initialData) {
        await updatePost(initialData.id, title, content);
      } else {
        await createPost(title, content);
      }
      router.push("/profile");
    } catch (error) {
      console.error("Error submitting post:", error);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="max-w-2xl mx-auto">
      <form onSubmit={handleSubmit} className="space-y-6">
        <div>
          <label
            htmlFor="title"
            className="block text-sm font-medium text-gray-700"
          >
            Title
          </label>
          <div className="mt-1">
            <input
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-purple-500 focus:border-purple-500"
            />
          </div>
        </div>

        <div>
          <label
            htmlFor="content"
            className="block text-sm font-medium text-gray-700"
          >
            Content
          </label>
          <div className="mt-1">
            <textarea
              id="content"
              rows={6}
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
              className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-purple-500 focus:border-purple-500"
            />
          </div>
        </div>

        <div className="flex justify-end space-x-4">
          <Link
            href="/profile"
            className="px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-500"
          >
            Cancel
          </Link>
          <button
            type="submit"
            disabled={isSubmitting}
            className="px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-purple-600 hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-purple-500 disabled:opacity-50"
          >
            {isSubmitting
              ? "Saving..."
              : initialData
              ? "Update Post"
              : "Create Post"}
          </button>
        </div>
      </form>
    </div>
  );
}
