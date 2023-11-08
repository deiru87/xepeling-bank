import React from 'react';
import ReactDOM from 'react-dom';
import ReactRouterDom from 'react-router-dom';
import { Link, BrowserRouter, Route, Switch } from 'react-router-dom';
// import './App.css';
import './style.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import IconButton from '@material-ui/core/IconButton';
import AccountBalanceSharpIcon from '@material-ui/icons/AccountBalanceSharp';
import { SnackbarProvider } from 'notistack';
import Home from './routes/home';
import { Deposit, Withdraw, Account } from './routes/forms';

function App() {
  return (
    <div>
      <SnackbarProvider autoHideDuration={1000}>
        <BrowserRouter>
          <CssBaseline />
          <Container>
            <AppBar position="static">
              <Toolbar>
                <IconButton
                  edge="start"
                  color="inherit"
                  aria-label="open drawer"
                  component={Link}
                  to="/"
                >
                  <AccountBalanceSharpIcon />
                </IconButton>
                <h2>Xepelin Bank</h2>
              </Toolbar>
            </AppBar>
            <Switch>
              <Route path="/account" component={Account} />
              <Route path="/deposit" component={Deposit} />
              <Route path="/withdraw" component={Withdraw} />
              <Route path="/" component={Home} />
            </Switch>
          </Container>
        </BrowserRouter>
      </SnackbarProvider>
    </div>
  );
}

export default App;
