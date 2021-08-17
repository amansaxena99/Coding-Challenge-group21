import React, { Component } from "react";
import { BrowserRouter as Link } from "react-router-dom";
import axios from "axios";


export default class Login extends Component {
    

    state = {
        details: {}
    }

    handleSubmit = event => {
        event.preventDefault();
        var url = 'https://grads-coding-group-21.uc.r.appspot.com/user/' + document.getElementById("email").value;
        console.log(url);
        axios.get(url)
        .then(response => {
            console.log(response)
            const user = response.data
            this.setState ({
                ...this.state,
                details: user
            });
            if (this.state.details.email === document.getElementById("email").value) {
                console.log("not null");
                console.log(this.state.details);
                this.props.history.push("/userData", {state: this.state.details});
            } else {
                console.log("null");
                console.log(this.state.details)
            }
        })
    }

    render() {
        return (
            <div className="App">
      <nav className="navbar navbar-expand-lg navbar-light fixed-top">
          <div className="container">
            <Link className="navbar-brand" to={"/"}>Social Desking</Link>
          </div>
      </nav>
                <div className="auth-wrapper">
                    <div className="auth-inner">
                    <form onSubmit={this.handleSubmit}>
                            <h3>Log In</h3>

                            <div className="form-group">
                                <label>Email address</label>
                                <input type="email" className="form-control" placeholder="Enter email" id="email"/>
                            </div>

                            <button type="submit" className="btn btn-primary btn-block" >Submit</button>
                        </form>
                    </div>
                </div> 
                </div>
        );
    }
}
