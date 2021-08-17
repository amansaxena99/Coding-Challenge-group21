import React from 'react';
import {BrowserRouter,Route, Switch} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table/dist/react-bootstrap-table.min.css';
import './App.css';

import Admin from "./components/AdminHome";
import Login from "./components/login.component";
import CreateBooking from "./components/booking/createBooking";
import EditBooking from "./components/booking/editBooking";
import UserDisplay from "./components/userDisplay.component";

let userId = ""
let email = ""

function App() {

  document.addEventListener('changeEmail', function(data) {
    console.log(data.detail)
    email = data.detail
  })
  document.addEventListener('changeUserId', function(data) {
    console.log(data.detail)
    userId = data.detail
  })
  return (
    <BrowserRouter>
     <Switch>
        <Route exact path='/admin' component={Admin}/>
        <Route exact path='/' component={Login}/>
        <Route path="/userData" component={UserDisplay} />
        <Route exact path='/createBooking' render = {
          (props) => (<CreateBooking {...props} userId = {userId} email= {email}/>)} />
        <Route exact path='/editbooking/:bookingId/:floorId/:buildingId/:date/:location' render = {
          (props) => (<EditBooking {...props} userId = {userId} email= {email}/>)} />
      </Switch>
   </BrowserRouter>
  );
}

export default App;
