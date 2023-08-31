-- Insert initial data for Issuer table
INSERT INTO issuer (lei, legal_name, description)
VALUES
    ( '123456789', 'Issuer A', 'Issuer A Description'),
    ( '987654321', 'Issuer B', 'Issuer B Description');

-- Insert initial data for Venue table
INSERT INTO venue ( name, city, country)
VALUES
    ( 'Venue X', 'City X', 'Country X'),
    ( 'Venue Y', 'City Y', 'Country Y');

-- Insert initial data for Instrument table
INSERT INTO instrument ( isin, currency, type, description, effective_date, status, issuer_id)
VALUES
    ( 123, 'USD', 'Stock', 'Stock Instrument', '2023-01-01', 'Active',1 ),
    ( 456, 'EUR', 'Bond', 'Bond Instrument', '2023-02-01', 'Inactive',2);
3
-- Insert initial data for Member table
INSERT INTO member ( lei, legal_name, description, address, venue_id)
VALUES
    (111111111, 'Member A', 'Member A Description', 'Address A', 1),
    (222222222, 'Member B', 'Member B Description', 'Address B', 2);

INSERT INTO venue_instrument (venue_id, instrument_id)
VALUES
    (1, 1),
    (2, 2);
