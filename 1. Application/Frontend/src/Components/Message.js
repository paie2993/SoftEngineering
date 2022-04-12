import axios from 'axios';
import React, { useEffect, useState } from 'react'

export default class Message extends React.Component {
    state = {
        message: ''
      }
    
      componentDidMount() {
        axios.get(`http://localhost:8080/message`)
          .then(res => {
            const message = res.data.text;
            this.setState({ message });
          })
      }

    render() {
        return (
            <>
                <h1>{this.state.message}</h1>
            </>
        )
    }
}
