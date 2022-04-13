import React from 'react';

import Navbar from './components/statics/navbar/Navbar';
import Footer from './components/statics/footer/Footer';
import Home from './pages/home/Home';
import Login from './pages/login/Login';
import Cadastro from './pages/cadastro/Cadastro';
import ListarUsuarios from './components/statics/usuarios/ListarUsuarios';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Provider } from 'react-redux';

import store from './store/store';

import './App.css';

function App() {
  return (
    <Provider store={store}>

      <Router>
        <Navbar />
        <Switch>
          <div style={{ minHeight: '100vh' }}>

            <Route exact path='/'>
              <Login />
            </Route>

            <Route exact path='/login'>
              <Login />
            </Route>

            <Route exact path='/home'>
              <Home />
            </Route>

            <Route exact path='/cadastro'>
              <Cadastro />
            </Route>

            <Route exact path='/usuario'>
              <ListarUsuarios />
            </Route>

          </div>
        </Switch>
        <Footer />
      </Router>
      </Provider>
  );
}

export default App;
