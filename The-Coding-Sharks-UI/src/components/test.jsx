import React from 'react';
import RandomDestination from './randomDestination';

const Test = () => {
  // Mock tripId to simulate passing data to the RandomDestination component
  const mockTripId = 1; // Example tripId, can be any number

  return (
    <div>
      <h1>Test RandomDestination Component</h1>
      {/* Passing the mock tripId to RandomDestination */}
      <RandomDestination tripId={mockTripId} />
    </div>
  );
};

export default Test;