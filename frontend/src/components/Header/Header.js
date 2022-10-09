import { AppBar, Box, Button, CssBaseline, Stack, Toolbar, Typography } from "@mui/material";
import { useKeycloak } from "@react-keycloak/web";
import { Link } from "react-router-dom";

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
          <CssBaseline />
          <Toolbar>
            <Typography variant="h4">
              Spring Batch
            </Typography>
            <Box sx={{ flexGrow: 1 }} />
            <Stack spacing={2} direction="row">
              <div>
                <Link to="/">Home</Link>
              </div>
              <div>
                {keycloak.authenticated && <Link to="/batch">Batch</Link>}
              </div>
            </Stack>
            <Box sx={{ flexGrow: 1 }} />
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
