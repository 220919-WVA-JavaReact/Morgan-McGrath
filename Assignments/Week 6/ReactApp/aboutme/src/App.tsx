import React from 'react';
import logo from './logo.svg';
import './App.css';
import Header from './components/header/header';
import Footer from './components/footer/Footer';
import Home from './components/home/Home';
import Nav from './components/nav/Nav';
import TTAAL from './components/ttaal/TTAAL';
import {BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <>
      <Header />
      <BrowserRouter>
      <Nav />
      <Routes>
          <Route path='/' element={<Home />}/>
          <Route path='/TTAAL' element={<TTAAL />}/>
      </Routes>
      {/* <Home /> */}
      </BrowserRouter>
      <Footer />
    </>
  );
}

export default App;
