import React, { Component } from "react";

import { BrowserRouter as Link } from "react-router-dom";
// import { BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import Card from "react-bootstrap/Card";
import { Button } from 'react-bootstrap';
import axios from "axios";

export default class UserDisplay extends Component {

    state = {
        details: {}
    }

    componentDidMount() {
        console.log('here');
        console.log(this.props.location.state.state);
        console.log('here2');
    }

    handleCreateBooking() {
        console.log(this.props.location.state.state.userID);
        const customEvent = new CustomEvent("changeUserId", {
          detail: this.props.location.state.state.userID
        });
        console.log("ce : " + customEvent.detail);
        document.dispatchEvent(customEvent);
        const customEvent1 = new CustomEvent("changeEmail", {
            detail: this.props.location.state.state.email
          });
          console.log("ce : " + customEvent1.detail);
          document.dispatchEvent(customEvent1);

        this.props.history.push("/createBooking");
      }

    handleEditBooking(bookingId, bId, fId, date, loc) {
        console.log(this.props.location.state.state.userID);
        const customEvent = new CustomEvent("changeUserId", {
          detail: this.props.location.state.state.userID
        });
        console.log("ce : " + customEvent.detail);
        document.dispatchEvent(customEvent);
        const customEvent1 = new CustomEvent("changeEmail", {
            detail: this.props.location.state.state.email
          });
          console.log("ce : " + customEvent1.detail);
          document.dispatchEvent(customEvent1);

        this.props.history.push("/editbooking/" + bookingId + "/" + fId + "/" + bId + "/" + date + "/" + loc);
    }

    async handleDeleteBooking(bookingId) {
        console.log("bookingid = " + bookingId);

        await axios({
          method: 'delete', 
          url: `https://grads-coding-group-21.uc.r.appspot.com/deleteBooking/${bookingId}`
        })
        .then(res => {
          console.log(res);
        })
        var url = 'https://grads-coding-group-21.uc.r.appspot.com/user/' + this.props.location.state.state.email;
        console.log(url);
        await axios.get(url)
        .then(response => {
            console.log(response)
            const user = response.data
            this.setState ({
                ...this.state,
                details: user
            });
            console.log(this.state.details);
            this.props.history.push("/userData", {state: this.state.details});
        })
    }
    
    render() {
        
        const info = this.props.location.state.state.bookings;
        return (
                <div className="App">
                <nav className="navbar navbar-expand-lg navbar-light fixed-top">
                    <div className="container">
                        <Link className="navbar-brand" to={"/"}>Social Desking</Link>
                    </div>
                </nav>
                <div className="auth-wrapper">
                    <div class="container">
                        <div class="row gutters">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="account-settings">
                                            <div class="user-profile">
                                                <div class="user-avatar">
                                                    <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Maxwell Admin"/>
                                                </div>
                                                <h2 class="user-name" >{this.props.location.state.state.name}</h2>
                                                <h4 class="user-email">{this.props.location.state.state.email}</h4>
                                                <h3 class="user-email">Team Name: {this.props.location.state.state.team.teamName}</h3>
                                                <h3 class="user-email">Team ID: {this.props.location.state.state.team.teamId}</h3>
                                                <button type="submit" className="btn btn-primary btn-block" onClick={(e) => this.handleCreateBooking(e)}>New Booking</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                            <div className="row">
                                {this.props.location.state.state.bookings ? this.props.location.state.state.bookings.map(currbooking => {           
                                    return(
                                    <div className="col">
                                        <Card style={{ width: "22rem" }}>
                                        <Card.Body>
                                        <Card.Title className="mb-2 text-muted">
                                            Booking ID: {currbooking.bookingID}
                                        </Card.Title>
                                        <Card.Subtitle>Date: {currbooking.bDate}</Card.Subtitle>
                                        <Card.Text>
                                        Building ID: {currbooking.buildingId}
                                            <br/>
                                            Floor ID: {currbooking.floorId}
                                            <br/>
                                            Location: {currbooking.location}
                                            <br/>
                                        </Card.Text>
                                        <Button className="edit-btn" variant="primary" onClick = {(e) => this.handleEditBooking(currbooking.bookingID, currbooking.floorId, currbooking.buildingId, currbooking.bDate, currbooking.location)}>Edit</Button>
                                        <Button className="delete-btn" variant="danger" onClick = {(e) => this.handleDeleteBooking(currbooking.bookingID)}>Delete</Button>
                                        </Card.Body>
                                    </Card>
                                    </div>
                                    );
                                }) : "No current bookings"}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}