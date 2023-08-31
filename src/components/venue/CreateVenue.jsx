import  { useState } from 'react';
import axios from 'axios';

export default function Form() {
 const [name, setName] = useState("");
  const [country,setCountry] = useState("");
  const [city,setCity] = useState("");
//   const [instrumentId, setInstrumentId] = useState("");
  const [message, setMessage] = useState("");

  let handleSubmit = async (e) => {

    e.preventDefault();

    const formData = {
      name,
      city,
      country,
    //   instrumentId
    };

    try {
      const res = await axios.post(
        "http://localhost:8080/api/venues",
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      console.log(res.data);

      if (res.status === 200) {
        setMessage("Venue created succesfully ")
        setTimeout(() => {
          window.location.reload();

        }, 1500);
      } else {
        setMessage("Error occured")
      }

    } catch (err) {
      console.log(err);
    }

    <div className="text-lg font-medium">{message ? <p>{message}</p> : null}</div>
  };

  return (
    <div className="">
      <h2>Create Venue</h2>
      <form className="margine" onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          id="name"
          placeholder="Legal Name"
          onChange={(e) => setName(e.target.value)}
          
        />

        <input
          type="text"
          name="city"
          id="city"
          placeholder="City"
          onChange={(e) => setCity(e.target.value)}
          
        />

        <input

          type="text"
          name="country"
          id="country"
          placeholder="Country"
          onChange={(e) => setCountry(e.target.value)}

        />

     

        {/* <input
          type="text"
          name="instrumentId"
          id="instrumentId"
          placeholder="Instrument ID"
          onChange={(e) => setInstrumentId(e.target.value)}

        /> */}
        <button
          type="submit"
        >
          Create
        </button>
      </form>
      <div className="flex justify-center mt-5">{message ? <p>{message}</p> : null}</div>
    </div>

  );

}