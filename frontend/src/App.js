import Container from '@mui/material/Container';
import './App.css';
import DataList from './components/DataList/DataList';
import Header from './components/Header/Header';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Welcome from './components/Welcome/Welcome';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './auth/keycloak';
import PrivateRoute from './auth/PrivateRoute';

function App() {
  const onEventHandler = async (event, error) => {
    console.log(event);
    if (event === 'onAuthSuccess') {
      if (keycloak.authenticated) {
          console.log(keycloak);
          console.log(keycloak.token);
          console.log(keycloak.tokenParsed);
      }
  }
  }

  return (
    <div>
      <ReactKeycloakProvider authClient={keycloak} onEvent={onEventHandler} >
        <BrowserRouter>
          <Container maxWidth="sm">
            <Header />

            <Routes>
              <Route path="/" element={<Welcome />} />
              <Route path="/batch"
                element={
                  <PrivateRoute>
                    <DataList />
                  </PrivateRoute>
                } />
            </Routes>
          </Container>

        </BrowserRouter>
      </ReactKeycloakProvider>
    </div>
  );
}

export default App;
