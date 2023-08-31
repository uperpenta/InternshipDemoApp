import  { useState } from 'react';
import axios from 'axios';

export default function Form(){
    const [id,setId]=useState();
    const [instrumentId,setInstrumentId] = useState();
    const [message, setMessage]=useState("");


    let handleSubmit = async (e) => {
        e.preventDefault();
      
        const formData = {
          id,
          instrumentId
        };
      
        try {
          const res = await axios.put(
            `http://localhost:8080/api/venues/${id}/instruments/${instrumentId}`,
            formData
          );
          console.log(res.data);
          if (res.status === 200) {
            setMessage("Instrument added succesfully ");
            // setTimeout(() => {
            //     window.location.reload();
            // }, 1500);
          } else {
            setMessage("Error occured");
          }
        } catch (err) {
          console.log(err);
        }
      };


    return (
        <div>
            <h2>Add Instrument to Venue</h2>
            <form className="margine" onSubmit={handleSubmit}>
                <input 
                    type="text"
                    name="id"
                    id="venueid"
                    
                    placeholder="Venue Id"
                    onChange={(e)=> setId(e.target.value)}
                />

                <input 
                    type="text"
                    name="instrumentId"
                    
                    id="instrumentid"
                    placeholder="Instrument Id"
                    onChange={(e)=> setInstrumentId(e.target.value)}
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