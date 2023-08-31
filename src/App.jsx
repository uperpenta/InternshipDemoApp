
import './App.css'

import ListMembers from './components/ListMembers'
import CreateMember from './components/CreateMember'
import SearchMember from './components/SearchMember'
import UpdateMember from './components/UpdateMember'
import DeleteMember from './components/DeleteMember'
import  { useState } from 'react';
import ListVenues from './components/venue/ListVenues'
import CreateVenue from './components/venue/CreateVenue'
import AddVenueToMember from './components/AddVenueToMember'
import AddInstrumentToVenue from './components/venue/AddInstrumentToVenue'
import GetInstrumentsByVenue from './components/venue/GetInstrumentsByVenue'
import DeleteInstrumentFromVenue from './components/venue/DeleteInstrumentFromVenue'
import UpdateVenue from './components/venue/UpdateVenue'
import DeleteVenue from './components/venue/DeleteVenue'
import ListInstruments from './components/instrument/ListInstruments'
import CreateInstrument from './components/instrument/CreateInstrument'
import UpdateInstrument from './components/instrument/UpdateInstrument'
import DeleteInstrument from './components/instrument/DeleteInstrument'
import AddIssuerToInstrument from './components/instrument/AddIssuerToInstrument'
import GetIssuerByInstrument from './components/instrument/GetIssuerByInstrument'
import DeleteIssuerFromInstrument from './components/instrument/DeleteIssuerFromInstrument'
import ListIssuers from './components/issuer/ListIssuers'
import CreateIssuer from './components/issuer/CreateIssuer'
import UpdateIssuer from './components/issuer/UpdateIssuer'
import DeleteIssuer from './components/issuer/DeleteIssuer'



function App() {
  
  const [showMembers, setShowMembers] = useState(false);
  const [showVenues , setShowVenues] = useState(false);
  const [showInstruments,setShowInstruments] = useState(false);
  const [showIssuers,setShowIssuers] = useState(false);


  const toggleMembers = () => {
    setShowMembers(!showMembers);
    setShowVenues(false); 
    setShowInstruments(false);
    setShowIssuers(false);
  };

  const toggleVenues=() =>{
    setShowVenues(!showVenues);
    setShowMembers(false); 
    setShowInstruments(false);
    setShowIssuers(false);
  };
  const toggleInstruments=() =>{
    setShowInstruments(!showInstruments);
    setShowMembers(false); 
    setShowVenues(false);
    setShowIssuers(false);
  };

  const toggleIssuers = () => {
    setShowIssuers(!showIssuers);
    setShowMembers(false); 
    setShowVenues(false);
    setShowInstruments(false);
  };

  return (
    <div className= 'alignleft background-image' >
      <nav className="navbar">
      <button onClick={toggleMembers}>Members</button>
      <button onClick = {toggleVenues} >Venues</button>
      <button onClick = {toggleInstruments} >Instruments</button>
      <button onClick={toggleIssuers} >Issuers</button>
      </nav>

      {showMembers && (
        <div>
          <ListMembers/>
          <CreateMember />
          <SearchMember/>
          <UpdateMember />
          <DeleteMember />
          <AddVenueToMember/>
        </div>
      )}
      {showVenues && (
        <div>
          <ListVenues/>
          <CreateVenue/>
          <UpdateVenue/>
          <DeleteVenue/>
          <AddInstrumentToVenue/>
          <GetInstrumentsByVenue/>
          <DeleteInstrumentFromVenue/>
        </div>
      )}
      {showInstruments && (
        <div>
          <ListInstruments/>
          <CreateInstrument/>
          <UpdateInstrument/>
          <DeleteInstrument/>
          <AddIssuerToInstrument/>
          <GetIssuerByInstrument/>
          <DeleteIssuerFromInstrument/>
        </div>
      )}
      {showIssuers && (
        <div>
          <ListIssuers/>
          <CreateIssuer/>
          <UpdateIssuer/>
          <DeleteIssuer/>
        </div>
      )}



    </div>

   
  );

}


export default App
