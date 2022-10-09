import Keycloak from "keycloak-js";
import Constants from "../utils/Constants";

const keycloak = new Keycloak({
    url: Constants.AUTH_URL,
    realm: Constants.AUTH_REALM,
    clientId: Constants.AUTH_CLIENT_ID
});

export default keycloak;
