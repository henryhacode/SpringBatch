import jwt_decode from "jwt-decode";
import Constants from "./Constants";

export function storeToken(token) {
  localStorage.setItem(Constants.AUTH_TOKEN, token);
}

export function clearToken() {
  localStorage.removeItem(Constants.AUTH_TOKEN);
}

export function getToken() {
  return localStorage.getItem(Constants.AUTH_TOKEN);
}

export function isRole(role) {
  try {
    const auth = jwt_decode(getToken());
    return auth?.realm_access?.roles?.includes(role);
  } catch (e) {
    return false;
  }
}
