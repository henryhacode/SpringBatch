import { useKeycloak } from "@react-keycloak/web";

export default function Welcome(props) {
    const { keycloak } = useKeycloak();

    return (
        <div>
            <h3>Welcome to Spring Batch</h3>
            {!keycloak.authenticated && <h4>Login to access more functions</h4>}
        </div>
    )
}