import axios from "axios";
import { getToken } from "./Utils";
import Constants from "./Constants";

const axiosInstance = axios.create({
  baseURL: Constants.BACKEND_URL
});

axiosInstance.interceptors.request.use(
  (config) => {
    const { origin } = new URL(config.url, Constants.BASE_URL);
    const allowedOrigin = [Constants.BASE_URL];
    const token = getToken();

    if (token && allowedOrigin.includes(origin)) {
      config.headers = { Authorization: `Bearer ${token}` };
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
