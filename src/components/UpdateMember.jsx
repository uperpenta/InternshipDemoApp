import  { useState } from 'react';
import axios from 'axios';
import '../App.css'

export default function Form() {

    const [legalName, setLegalName] = useState("");
    const [address, setAddress] = useState("");
    const [description, setDescription] = useState("");
    const [lei, setLEI] = useState("");
    const [venueId, setVenueId] = useState("");
    const [message, setMessage] = useState("");
    const [id, setId] = useState();



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
            const res = await axios.put(
                `http://localhost:8080/api/members/${id}`,
                formData,
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                }
            );
            console.log(res.data);
            if (res.status === 200) {
                setMessage("Member updated succesfully ")
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
            <h2>Update Member</h2>
            <form className="margine" onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="id"
                    id="memberid"
                    placeholder="Member Id"
                    onChange={(e) => setId(e.target.value)}
                    className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"


                />

                <input
                    type="text"
                    name="legalName"
                    id="legalName"
                    placeholder="Legal Name"
                    onChange={(e) => setLegalName(e.target.value)}
                    className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"



                />

                <input

                    type="text"
                    name="address"
                    id="adress"
                    placeholder="Adress"
                    onChange={(e) => setAddress(e.target.value)}
                    className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"

                />

                <input
                    type="text"
                    name="description"
                    id="description"
                    placeholder="Description"
                    onChange={(e) => setDescription(e.target.value)}
                    className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
                />

                <input
                    type="text"
                    name="lei"
                    id="lei"
                    placeholder="LEI"
                    onChange={(e) => setLEI(e.target.value)}
                    className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
                />

                <input
                    type="text"
                    name="venueId"
                    id="venueId"
                    placeholder="Venue ID"
                    onChange={(e) => setVenueId(e.target.value)}
                    className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
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