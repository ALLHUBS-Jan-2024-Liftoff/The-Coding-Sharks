import React, { useState } from 'react';

const Packlist = () => {
  const [itemsList, setItemsList] = useState([]);
  const [newItem, setNewItem] = useState('');

  const addItem = () => {
    if (newItem.trim() === '') return; //checks if string is empty + ignores whitespace

    const newItemObject = {
      id: itemsList.length ? itemsList[itemsList.length - 1].id + 1 : 1,
      name: newItem, 
    };
    setItemsList([...itemsList, newItemObject]);
    setNewItem('');
  };

  const deleteItem = (id) => {
    setItemsList(itemsList.filter(item => item.id !== id));
  };

  return (
    <div>
      <h2>Travel Packlist</h2>
      <input 
        type="text" 
        value={newItem} 
        onChange={(e) => setNewItem(e.target.value)} 
        placeholder="Add new item" 
      />
      <button onClick={addItem}>Add Item</button>
      <ul>
        {itemsList.map(item => (
          <li key={item.id}>
            {item.name}
            <button onClick={() => deleteItem(item.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Packlist;
