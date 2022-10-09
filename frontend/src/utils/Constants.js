const env = process.env;

const Constants = {
  BASE_URL: env.REACT_APP_BASE_URL,
  BACKEND_URL: env.REACT_APP_BACK_END,
  AUTH_URL: env.REACT_APP_AUTH_URL,
  AUTH_REALM: env.REACT_APP_AUTH_REALM,
  AUTH_CLIENT_ID: env.REACT_APP_AUTH_CLIENT_ID
}

console.log(Constants);
export default Constants;
