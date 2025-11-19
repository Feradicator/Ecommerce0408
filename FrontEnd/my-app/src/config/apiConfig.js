import axios from "axios";

export const API_BASE_URL = "https://ecommerce0408.onrender.com";
// export const API_BASE_URL = "http://localhost:8080";

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json"
  }
});

// Add interceptor to add token only when present
api.interceptors.request.use((config) => {
  const jwt = localStorage.getItem("jwt");

  if (jwt) {
    config.headers["Authorization"] = `Bearer ${jwt}`;
  }

  return config;
});
