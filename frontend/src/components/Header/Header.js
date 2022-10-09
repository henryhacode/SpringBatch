import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";
import { useKeycloak } from "@react-keycloak/web";

export default function (props) {
  const { keycloak } = useKeycloak();

  const login = (e) => {
    keycloak.login();
  }

  const logout = (e) => {
    keycloak.logout();
  }

  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="fixed">
          <Toolbar>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Spring Batch
            </Typography>
            <div>
              {!keycloak.authenticated && <Button color="inherit" onClick={login}>Login</Button>}
            </div>
            <div>
              {keycloak.authenticated &&
                <div>
                  <span>Welcome {keycloak.tokenParsed.name}</span>
                  <Button color="inherit" onClick={logout}>Logout</Button>
                </div>
              }
            </div>
          </Toolbar>
        </AppBar>
      </Box>
      <Toolbar />
    </div>
  )
}
