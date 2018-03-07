#### 下载详情列表接口
> GET     `api/reports`

**参数** *Content-Type: application/x-www-form-urlencoded*

| 参数                  | 必要  | 说明                                 |
|:---------------------|:-----|:-------------------------------------|
| createDate           |      | 创建时间, `yyyy-MM-dd`                |
| siteId               |      | 站点, 多个之间用英文逗号(`,`)隔开       |
| accountId            |      | 账号(店铺), 多个之间用英文逗号(`,`)隔开 |
| reportType           |      | 报表类型                              |
| reportDownloadStatus |      | 报表下载状态                          |
| department           |      | 所属部门                              |
| reportVerifyStatus   |      | 报表审核状态                          |
| reviewUserId         |      | 报表审核人                            |

**响应**
```json
{
  "success": true,
  "message": null,
  "errorCode": null,
  "data": {
      	"total": 2661, //总数
      	"rows": [{
                 	"id": 4350,  //主键
                 	"accountId": 372, //账号ID
                 	"siteId": 24, //站点ID
                 	"reportStatus": "FAILED", //当前状态
                 	"reportTypeId": "CustomTransaction", //报表类型
                 	"reportFilePath": null, //报表路径
                 	"reportDate": "2017-10-31 00:00:00", //生成时间
                 	"succeedTimeStamp": null, //成功时间
                 	"createdTimeStamp": "2017-10-31 15:00:16",//创建时间
                 	"lastUpdatedTimeStamp": "2017-10-31 15:00:16",//最后更新时间
                 	"startDate": null, //开始时间
                 	"endDate": null, //结束时间
                 	"failureReason": null,//失败原因
                 	"verifyStatus": null,//审核状态
                 	"verifyUserId": null,//审核用户ID
                 	"verifyDate": null,//审核时间
                 	"fileName": "小包-亚马逊A组美国OYJ2017-10-31", //文件名
                 	"departmentName": "小包-亚马逊A组", //部门
                 	"siteName": "美国",//美国
                 	"accountName": "OYJ",//账号
                 	"userName": "谢犇"//账号
                 }, {
                 	"id": 4351,
                 	"accountId": 372,
                 	"siteId": 40,
                 	"reportStatus": "FAILED",
                 	"reportTypeId": "CustomTransaction",
                 	"reportFilePath": null,
                 	"reportDate": "2017-10-31 00:00:00",
                 	"succeedTimeStamp": null,
                 	"createdTimeStamp": "2017-10-31 15:00:16",
                 	"lastUpdatedTimeStamp": "2017-10-31 15:00:16",
                 	"startDate": null,
                 	"endDate": null,
                 	"failureReason": null,
                 	"verifyStatus": null,
                 	"verifyUserId": null,
                 	"verifyDate": null,
                 	"fileName": "小包-亚马逊A组加拿大OYJ2017-10-31",
                 	"departmentName": "小包-亚马逊A组",
                 	"siteName": "加拿大",
                 	"accountName": "OYJ",
                 	"userName": "谢犇"//审核人
                 }//......
      	]
  }
}
```

#### 下载报表文件接口
> POST     `api/reports/download`

**参数** *Content-Type: application/x-www-form-urlencoded*


| 参数      | 必要  | 说明            |
|:---------|:-----|:---------------|
| reportIds | `是` | 下载的报文文件ID,账号(店铺), 多个之间用英文逗号(`,`)隔开 |



#### 报表文件审核通过接口
> PUT     `api/reports/verifyPass`

**参数** *Content-Type: application/json*

```json
{
  "reportId": 123
}
```



#### 报表文件审核不通过接口
> PUT     `api/reports/verifyFail`

**参数** *Content-Type: application/json*

```json
{
  "reportId": 123,
  "failReason": "不通过原因"
}
```

#### 清除失败状态文件接口
> PUT     `api/reports/cleanFailFile`

**参数** *Content-Type: application/json*

```json
{
	"id": 4351,
	"accountId": 372,
	"siteId": 40,
	"reportStatus": "FAILED",
	"reportTypeId": "CustomTransaction",
	"reportFilePath": null,
	"reportDate": "2017-10-31 00:00:00",
	"succeedTimeStamp": null,
	"createdTimeStamp": "2017-10-31 15:00:16",
	"lastUpdatedTimeStamp": "2017-10-31 15:00:16",
	"startDate": null,
	"endDate": null,
	"failureReason": null,
	"verifyStatus": null,
	"verifyUserId": null,
	"verifyDate": null,
	"fileName": "小包-亚马逊A组加拿大OYJ2017-10-31",
	"departmentName": "小包-亚马逊A组",
	"siteName": "加拿大",
	"accountName": "OYJ"
}
```
**响应**

```json
{
    "success": true,
    "message": "文件解析错误",
    "data": {},
    "errorCode": null
}
```

#### 手动添加报表文件上传接口
> POST     `api/reports/uploadFile`

**参数** *Content-Type: multipart/form-data*

| 参数      | 必要  | 说明            |
|:---------|:-----|:---------------|
| reportId | `是` | 下载的报文文件ID |
| file     | `是` | 文件            |

**响应**
```json
{
    "success": true,
    "message": "文件解析错误",
    "data": {},
    "errorCode": null
}
```

#### 报表详细列表接口
> GET     `api/reportDetails`

**参数**   *Content-Type: application/x-www-form-urlencoded*

| 参数        | 必要  | 说明                                 |
|:-----------|:-----|:-------------------------------------|
| dateFrom   |      | 起始时间, `yyyy-MM-dd`                |
| dateThru   |      | 截止时间, `yyyy-MM-dd`                |
| siteId     |      | 站点, 多个之间用英文逗号(`,`)隔开       |
| accountId  |      | 账号(店铺), 多个之间用英文逗号(`,`)隔开 |
| department |      | 所属部门                              |

**响应**
```json
{
    "success": true,
    "message": null,
    "data": {
        "total": 1,
        "rows": [
            {
                "id": 1, //主键Id
                "accountId": 371, //账户Id
                "siteId": 24, //站点Id
                "reportDate": "2017-11-01 16:18:47", //报表时间
                "settlementId": "2",//结算号
                "transactionTypeId": 3,//交易类型
                "amazonOrderId": "4",//亚马逊订单号
                "description": "4",//描述
                "sellerSku": "3",//卖家SKU
                "quantity": 4,//商品数量
                "marketplaceId": 3,//市场
                "fulfilmentId": 4,//发货方式
                "orderCityName": "4",//订单城市名称
                "orderStateName": "34",//订单州,区域名称
                "orderPostal": "3",//包裹号
                "currencyCode": "34",//币种
                "exchangeRate": 1,//换算CNY汇率
                "productSales": 0,//商品销售额
                "shippingCredits": 0,//卖家优惠
                "giftWrapCredits": 0,//礼物包装积分
                "amazonCredits": 4,//亚马逊积分返点
                "promotionalRebates": 0,//促销折扣
                "salesTax": 0,//销售税费
                "fbaFees": 0,//FBA运费
                "otherTransactionFees": 0,//其他交易费
                "otherFees": 0,//其他费用
                "totalFees": 0,//总费用
                "departmentName": "小包-亚马逊A组",//部门
                "siteName": "美国",//站点
                "accountName": "ZG"//账号
            }
        ]
    },
    "errorCode": null
}
```

#### 报表详细导出接口
> POST     `api/reportDetails/export`

**参数** *Content-Type: application/x-www-form-urlencoded*

| 参数        | 必要  | 说明                                 |
|:-----------|:-----|:-------------------------------------|
| dateFrom   |      | 起始时间, `yyyy-MM-dd`                |
| dateThru   |      | 截止时间, `yyyy-MM-dd`                |
| siteId     |      | 站点, 多个之间用英文逗号(`,`)隔开       |
| accountId  |      | 账号(店铺), 多个之间用英文逗号(`,`)隔开 |
| department |      | 所属部门                              |

#### 店铺接口
> GET     `/api/account`

**参数** *Content-Type: application/x-www-form-urlencoded*

| 参数                  | 必要  | 说明                                 |
|:---------------------|:-----|:-------------------------------------|
| accountName            |      | 账号(店铺), 多个之间用英文逗号(`,`)隔开 |

**响应**
```json
[{
		"id": 371, //店铺Id
		"departmentName": "小包-亚马逊A组", //部门
		"accountName": "ZG", //店铺名称
		"accountCode": "ZG", //店铺简称
		"accountStatus": "ENABLED", //状态
		"createdTimeStamp": "2017-10-26 14:37:23", //创建时间
		"lastUpdatedTimeStamp": "2017-10-26 14:37:23" //最后更新时间
	}, {
		"id": 372,
		"departmentName": "小包-亚马逊A组",
		"accountName": "OYJ",
		"accountCode": "OYJ",
		"accountStatus": "ENABLED",
		"createdTimeStamp": "2017-10-26 14:37:23",
		"lastUpdatedTimeStamp": "2017-10-26 14:37:23"
	}
	//......
]
```

