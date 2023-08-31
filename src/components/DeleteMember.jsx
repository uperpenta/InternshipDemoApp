import  { useState } from 'react';
import axios from 'axios';

function DeleteMember() {
  const [memberId, setMemberId] = useState('');

  const handleInputChange = (event) => {
    setMemberId(event.target.value);
  };

  const handleDelete = () => {
    if (memberId) {
      axios.delete(`http://localhost:8080/api/members/${memberId}`)
        .then(() => {
          console.log('Member deleted successfully');
          // You can perform any additional actions after deletion
        })
        .catch(error => {
          console.error('Error deleting member:', error);
          // Handle error if needed
        });
    }
  };

  return (
    <div>
      <h2>Delete Member</h2>
      <label>Member ID:</label>
      <input
        type="text"
        value={memberId}
        onChange={handleInputChange}
      />
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}

export default DeleteMember;
