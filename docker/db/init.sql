SELECT 'CREATE DATABASE express_bank'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'user_service')\gexec



