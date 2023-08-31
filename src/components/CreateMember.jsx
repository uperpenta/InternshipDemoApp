import  { useState } from 'react';
import axios from 'axios';
import '../App.css';

export default function Form() {
  const [legalName, setLegalName] = useState("");
  const [address, setAddress] = useState("");
  const [description, setDescription] = useState("");
  const [lei, setLEI] = useState("");
  const [venueId, setVenueId] = useState("");
  const [message, setMessage] = useState("");

  let handleSubmit = async (e) => {

    e.preventDefault();

    const formData = {
      legalName,
      address,
      description,
      lei,
      venueId
    };

    try {
      const res = await axios.post(
        "http://localhost:8080/api/members",
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      console.log(res.data);

      if (res.status === 200) {
        setMessage("Member created succesfully ")
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
      <h2>Create Member</h2>
      <form className="margine" onSubmit={handleSubmit}>
        <input
          type="text"
          name="legalName"
          id="legalName"
          placeholder="Legal Name"
          onChange={(e) => setLegalName(e.target.value)}
          
        />

        <input
          type="text"
          name="address"
          id="adress"
          placeholder="Adress"
          onChange={(e) => setAddress(e.target.value)}
          
        />

        <input

          type="text"
          name="description"
          id="description"
          placeholder="Description"
          onChange={(e) => setDescription(e.target.value)}

        />

        <input
          type="text"
          name="lei"
          id="lei"
          placeholder="LEI"
          onChange={(e) => setLEI(e.target.value)}

        />

        <input
          type="text"
          name="venueId"
          id="venueId"
          placeholder="Venue ID"
          onChange={(e) => setVenueId(e.target.value)}

        />
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