INSERT INTO WALLETS(USER_ID, BALANCES, REGISTERED_TIMESTAMP, LAST_TIMESTAMP)
VALUES('12345', '{"items":[{"currency": "USD", "amount": "2000.00", "lastTimestamp": "2024-03-03T20:12:18.302007"}]}', NOW(), NOW());

INSERT INTO MERCHANTS(MERCHANT_ID, FIXED_AMOUNT_FEES)
VALUES('merchantId123', '{"items":[{"amount": "5.00", "currency": "USD"}]}');
