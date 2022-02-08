FROM rabbitmq:3.8.9-management-alpine

RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

# Enable Prometheus (see https://www.rabbitmq.com/prometheus.html)
RUN rabbitmq-plugins enable rabbitmq_prometheus
RUN rabbitmq-plugins enable rabbitmq_tracing
