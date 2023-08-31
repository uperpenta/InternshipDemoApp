import  { useState } from 'react';
import axios from 'axios';


function SearchMember() {
  const [legalName, setLegalName] = useState('');
  const [description, setDescription] = useState('');
  const [address, setAddress] = useState('');
  const [searchResults, setSearchResults] = useState([]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {



      const response = await axios.get('http://localhost:8080/api/members/search', {
        params: {
          legalName,
          description,
          address
        }
      });
      setSearchResults(response.data);
    } catch (error) {
      console.error('Error searching members:', error);
    }
  };

  return (
    <div>
      <h2>Search Members</h2>
      <form onSubmit={handleSubmit}>
        <label>Legal Name:</label>
        <input type="text" value={legalName} onChange={(e) => setLegalName(e.target.value)} />

        <label>Description:</label>
        <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />

        <label>Address:</label>
        <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} />

        <button type="submit">Search</button>
      </form>

      <h3>Search Results:</h3>
      <ul>
        {searchResults.map((member) => (
          <li key={member.id}>{member.legalName}, {member.description}, {member.address}</li>
        ))}
      </ul>
    </div>
  );
}

export default SearchMember;