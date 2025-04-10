docker compose build
docker compose -f compose-dev.yaml up -d
docker compose -f compose-dev.yaml stop frontend-app
cd ../edutie-frontend
bun run dev