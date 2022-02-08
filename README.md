# 交易類練習

* saga-choreography

  基本CQRS架構，該訂單交易流程如下。

  1. 建立一筆訂單，並且發送 OrderEvent 事件。
  2. 庫存及支付服務同時收到該事件，並且各自服務完成後發送 InventoryEvent、PaymentEvent 事件。
     * 更新商品庫存及新增一筆庫存消費紀錄。
     * 更新使用者帳戶餘額及新增交易紀錄。
  3. 利用 MQ 的確認(Acknowledge,ACK)機制以及樂觀鎖機制，確保滿足以下條件。
     * 該筆訂單資料只會有一個線程處理修改，不會同時多個線程處理修改。
     * ACK機制確保 InventoryEvent、PaymentEvent 事件都會消費及處理完畢。

     備註: 如果 InventoryEvent、PaymentEvent 事件同時消費，其中一個事件會因為樂觀鎖拋Exception，重新被消費。

* saga-orchestration
  
  中心化的服務(order-orchestrator)來統一管理跟處理這些操作。
  
  * 主要以API方式去溝通庫存及支付服務。
  * 並且 MQ 消費、MQ 生產以及 API 採用 Flux 寫法。

## Spring Stream

* Consumer : MQ 消費者
* Supplier : MQ 生產者
* Processor : MQ 消費者 + MQ 生產者
