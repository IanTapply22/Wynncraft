# Variables
DOCKER_COMPOSE_FILE = wynncraft_sdk/docker-compose.yml
SERVICE_PREFIX = wynncraft-
SERVER_SUFFIX = -server
PROXY_SUFFIX = -proxy

# Container lists
CONTAINERS = influxdb mongo postgres grafana lobby$(SERVER_SUFFIX) main$(SERVER_SUFFIX) velocity$(PROXY_SUFFIX)

# Grouped containers
DATABASE_CONTAINERS = $(foreach container, $(filter influxdb mongo postgres, $(CONTAINERS)), $(SERVICE_PREFIX)$(container))
DASHBOARD_CONTAINERS = $(foreach container, $(filter grafana, $(CONTAINERS)), $(SERVICE_PREFIX)$(container))
SERVER_CONTAINERS = $(foreach container, $(filter %$(SERVER_SUFFIX) %$(PROXY_SUFFIX), $(CONTAINERS)), $(SERVICE_PREFIX)$(container))

# Phony targets
.PHONY: help up up-build down start stop restart-dbs restart-dashboards restart-servers attach bash send-command

# Help command
help:
	@echo "Available commands:"
	@echo "  up         	   Start all services"
	@echo "  up-build          Build and start all services"
	@echo "  down          	   Stop all services"
	@echo "  start     		   Start a specific service"
	@echo "  stop      		   Stop a specific service"
	@echo "  restart-dbs       Restart database services"
	@echo "  restart-dashboards Restart dashboard services"
	@echo "  restart-servers   Restart server services"
	@echo "  stop-dbs          Stop all database services"
	@echo "  stop-dashboards   Stop all dashboard services"
	@echo "  stop-servers      Stop all server services"
	@echo "  attach            Attach to a running container. Use CTRL+Q to detach"
	@echo "  bash              Open a bash shell in a running container. Only meant to interact with container infrastructure"
	@echo "  send-command      Send a command to a Wynncraft server container"

# General control commands
up:
	docker compose -f $(DOCKER_COMPOSE_FILE) up -d

up-build:
	docker compose -f $(DOCKER_COMPOSE_FILE) up -d --build

down:
	docker compose -f $(DOCKER_COMPOSE_FILE) down

attach:
	docker compose -f $(DOCKER_COMPOSE_FILE) attach $(SERVICE_PREFIX)$(container)

bash:
	docker compose -f $(DOCKER_COMPOSE_FILE) exec $(SERVICE_PREFIX)$(container) bash

# Individual service control commands
start:
	docker compose -f $(DOCKER_COMPOSE_FILE) up -d $(SERVICE_PREFIX)$(service)

stop:
	docker compose -f $(DOCKER_COMPOSE_FILE) stop $(SERVICE_PREFIX)$(service)

# Grouped service control commands
restart-dbs:
	docker compose -f $(DOCKER_COMPOSE_FILE) restart $(DATABASE_CONTAINERS)

restart-dashboards:
	docker compose -f $(DOCKER_COMPOSE_FILE) restart $(DASHBOARD_CONTAINERS)

restart-servers:
	docker compose -f $(DOCKER_COMPOSE_FILE) restart $(SERVER_CONTAINERS)

stop-dbs:
	docker compose -f $(DOCKER_COMPOSE_FILE) stop $(DATABASE_CONTAINERS)

stop-dashboards:
	docker compose -f $(DOCKER_COMPOSE_FILE) stop $(DASHBOARD_CONTAINERS)

stop-servers:
	docker compose -f $(DOCKER_COMPOSE_FILE) stop $(SERVER_CONTAINERS)

# Wynncraft related commands

# Note: This only works with actual servers; not proxies
send-command:
	docker exec $(SERVICE_PREFIX)$(container)$(SERVER_SUFFIX) rcon-cli $(command)