import  { useState } from 'react';
import axios from 'axios';
import '../App.css'

export default function Form(){
    const [id,setId]=useState();
    const [venueId,setVenueId] = useState();
    const [message, setMessage]=useState("");


    let handleSubmit = async (e) => {
        e.preventDefault();

        const formData= {
            id,
            venueId
        };



        try {
            const res= await axios.put(`http://localhost:8080/api/members/${id}/venue/${venueId}`,
            formData,
            {

            });
            console.log(res.data);
            if (res.status === 200) {
                setMessage("Venue added succesfully ")
                // setTimeout(() => {
                //     window.location.reload();
                // }, 1500);
            } else {
                setMessage("Error occured")
            }
            } catch (err) {
                console.log(err);
            }
            <div className="text-lg font-medium">{message ? <p>{message}</p> : null}</div>


    };


    return (
        <div>
            <h2>Add Venue to Member</h2>
            <form  className="margine" onSubmit={handleSubmit}>
                <input 
                    type="text"
                    name="id"
                    id="memberid"
                    placeholder="Member Id"
                    onChange={(e)=> setId(e.target.value)}
                />

                <input 
                    type="text"
                    name="venueId"
                    id="venueid"
                    placeholder="Venue Id"
                    onChange={(e)=> setVenueId(e.target.value)}
                />


                <button
                    type="submit"
                    className="text-base font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5  dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
                >
                    Update
                </button>

                
            </form>
            <div className="flex justify-center mt-5">{message ? <p>{message}</p> : null}</div>
        </div>
    );




}