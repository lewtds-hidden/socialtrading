# Install dependencies
dependencies:
	pip install -r requirements.txt

# Launch the server
launch:
	python launcher.py

# Create the database
database:
	psql duber < db/schema.sql
	psql duber < db/fixtures.sql

test:
	PYTHONPATH=. py.test

test-continuously:
	PYTHONPATH=. py.test --looponfail
