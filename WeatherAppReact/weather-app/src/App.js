import React from "react";

import Titles from "./componets/Titles"
import Form from "./componets/Forms"
import Weather from "./componets/Weather"

const api_key = "3536f102f1af0a149873286f7103aa7b";

class App extends React.Component{
    state={
        temperature:undefined,
        city:undefined,
        country:undefined,
        humidity:undefined,
        description:undefined,
        error:undefined
    }

    getWeather = async (e) => {
        e.preventDefault()
        const city = e.target.elements.city.value;
        const country = e.target.elements.country.value;

        const api_call = await fetch(`http://api.openweathermap.org/data/2.5/weather?q=${city},${country}&units=metric&APPID=${api_key}`);
        const data = await api_call.json();
        
        
        if(city && country){
            console.log(data);
            this.setState({
                temperature: data.main.temp,
                city: data.name,
                country: data.sys.country,
                humidity:data.main.humidity,
                description: data.weather[0].description,
                error:""
            })
        } else{
            this.setState({
                temperature: undefined,
                city: undefined,
                country: undefined,
                humidity: undefined,
                description: undefined,
                error:"Please enter a value"
            })
        }
      
    }

    render(){
        return (
            <div> 
                <Titles /> 
                <Form getWeather={this.getWeather} />
                <Weather 
                temperature={this.state.temperature}
                city= {this.state.city}
                country= {this.state.country}
                humidity= {this.state.humidity}
                description= {this.state.description}
                error = {this.state.error}

                 />
            </div>
        );
    }
};

export default App;