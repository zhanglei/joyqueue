webpackJsonp([37],{ACOU:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("woOf"),s=a.n(i),r=a("T0gc"),o=a("fo4W"),n=a("95hR"),c=a("1a0f"),l=a("toiR"),d=a("RUZX"),u=a("4BiB"),p=a("DWQO"),b=a("M3bc"),g={name:"application",components:{myTable:r.a,myDialog:o.a,subscribeProducerApplyForm:d.default,subscribeConsumerApplyForm:u.default,cancelSubscribeApplyForm:p.default},mixins:[n.a],data:function(){return{searchData:{keyword:"",applyType:"ALL",status:-2},rejectDialog:{visible:!1,title:"同步应用",showFooter:!0},rejectData:{},tableData:{rowData:[],colData:[{title:"ID",key:"id"},{title:"申请类型",key:"apply.type",render:function(e,t){var a=void 0;switch(t.item.apply.type){case"SUBSCRIBE_PRODUCER_APPLY":a="订阅生产者";break;case"SUBSCRIBE_CONSUMER_APPLY":a="订阅消费者";break;case"CANCEL_SUBSCRIBE_APPLY":a="取消订阅";break;case"APP_USER_APPLY":a="申请成为APP联系人";break;case"APP_OWNER_APPLY":a="申请成为APP负责人"}return e("label",{},a)}},{title:"申请ID",key:"apply.id"},{title:"主题",key:"apply.topic.code"},{title:"应用",key:"apply.app.code"},{title:"申请描述",key:"apply.description"},{title:"申请人",key:"apply.createBy.code"},{title:"申请时间",key:"apply.createTime",render:function(e,t){return e("label",{},Object(l.a)(t.item.createTime,""))}},{title:"状态",key:"status",render:function(e,t){var a=void 0;switch(t.item.status){case-1:a="已删除";break;case 0:a="已取消";break;case 1:a="新建";break;case 2:a="待审批";break;case 3:a="已驳回";break;case 4:a="审批通过";break;case 5:a="待确认";break;case 6:a="已确认";break;case 7:a="待执行";break;case 8:a="已完成"}return e("label",{},a)}}],btns:[{txt:"编辑",method:"on-edit",isAdmin:!0},{txt:"批准",method:"on-approve",bindKey:"status",bindVal:2},{txt:"驳回",method:"on-reject",bindKey:"status",bindVal:2},{txt:"确认",method:"on-confirm",bindKey:"status",bindVal:5},{txt:"执行",method:"on-execute",bindKey:"status",bindVal:7},{txt:"删除",method:"on-del"}]},subscribeProducerEditDialog:{visible:!1,title:"订阅生产者",width:700,showFooter:!1},subscribeProducerEditData:{},subscribeConsumerEditDialog:{visible:!1,title:"订阅消费者",width:700,showFooter:!1},subscribeConsumerEditData:{},cancelSubscribeEditDialog:{visible:!1,title:"取消订阅",width:600,showFooter:!1},cancelSubscribeEditData:{}}},computed:{},methods:{openRejectDialog:function(e){this.rejectData=e,this.rejectDialog.visible=!0},dialogCancel:function(e){this[e].visible=!1,this.getList()},getSearchVal:function(){return{pagination:{page:this.page.page,size:this.page.size},query:{keyword:this.searchData.keyword,applyType:"ALL"===this.searchData.applyType?null:this.searchData.applyType,status:-2===this.searchData.status?null:this.searchData.status}}},edit:function(e){var t=this,a={};c.a.get(this.urlOrigin.findApply+"/"+e.id,{},{}).then(function(i){a=i.data||{},"SUBSCRIBE_PRODUCER_APPLY"===e.apply.type?(t.subscribeProducerEditData=Object(b.e)(a),t.subscribeProducerEditDialog.visible=!0):"SUBSCRIBE_CONSUMER_APPLY"===e.apply.type?(t.subscribeConsumerEditData=Object(b.e)(a),t.subscribeConsumerEditDialog.visible=!0):"CANCEL_SUBSCRIBE_APPLY"===e.apply.type&&(t.cancelSubscribeEditData=Object(b.e)(a),t.cancelSubscribeEditData.subscribeType="PRODUCER"===a.subscribeType?t.$store.getters.producerType:t.$store.getters.consumerType,t.cancelSubscribeEditDialog.visible=!0)})},approve:function(e){var t=s()({},e);this.update(this.urlOrigin.approve+"/"+t.id,{},"审批成功","审批失败")},reject:function(e){var t=this;c.a.put(this.urlOrigin.reject+"/"+this.rejectData.id,{},this.rejectData.suggestion).then(function(e){t.rejectDialog.visible=!1,t.$Message.success("驳回成功"),t.getList()})},confirm:function(e){var t=s()({},e);this.update(this.urlOrigin.confirm+"/"+t.id,{},"确认成功","确认失败")},execute:function(e){var t=s()({},e);this.update(this.urlOrigin.execute+"/"+t.id,{},"执行成功","执行失败")}},mounted:function(){this.getList()}},y={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticClass:"ml20 mt30"},[a("d-select",{staticClass:"left",staticStyle:{width:"15%"},attrs:{placeholder:"请选择 申请类型"},model:{value:e.searchData.applyType,callback:function(t){e.$set(e.searchData,"applyType",t)},expression:"searchData.applyType"}},[a("span",{attrs:{slot:"prepend"},slot:"prepend"},[e._v("申请类型")]),e._v(" "),a("d-option",{attrs:{value:"ALL"}},[e._v("全部")]),e._v(" "),a("d-option",{attrs:{value:"SUBSCRIBE_PRODUCER_APPLY"}},[e._v("订阅生产者")]),e._v(" "),a("d-option",{attrs:{value:"SUBSCRIBE_CONSUMER_APPLY"}},[e._v("订阅消费者")]),e._v(" "),a("d-option",{attrs:{value:"CANCEL_SUBSCRIBE_APPLY"}},[e._v("取消订阅")])],1),e._v(" "),a("d-select",{staticClass:"left",staticStyle:{width:"15%"},attrs:{placeholder:"请选择 待审批状态"},model:{value:e.searchData.status,callback:function(t){e.$set(e.searchData,"status",e._n(t))},expression:"searchData.status"}},[a("span",{attrs:{slot:"prepend"},slot:"prepend"},[e._v("待处理状态")]),e._v(" "),a("d-option",{attrs:{value:-2}},[e._v("全部")]),e._v(" "),a("d-option",{attrs:{value:2}},[e._v("待审批")]),e._v(" "),a("d-option",{attrs:{value:5}},[e._v("待确认")]),e._v(" "),a("d-option",{attrs:{value:7}},[e._v("待执行")])],1),e._v(" "),a("d-input",{staticClass:"left",staticStyle:{width:"15%"},attrs:{placeholder:"请输入申请信息"},model:{value:e.searchData.keyword,callback:function(t){e.$set(e.searchData,"keyword",t)},expression:"searchData.keyword"}}),e._v(" "),a("d-button",{attrs:{type:"primary"},on:{click:e.getList}},[e._v("查询"),a("icon",{staticStyle:{"margin-left":"5px"},attrs:{name:"search"}})],1)],1),e._v(" "),a("my-table",{attrs:{data:e.tableData,showPin:e.showTablePin,page:e.page},on:{"on-size-change":e.handleSizeChange,"on-current-change":e.handleCurrentChange,"on-selection-change":e.handleSelectionChange,"on-del":e.del,"on-approve":e.approve,"on-edit":e.edit,"on-reject":e.openRejectDialog,"on-confirm":e.confirm,"on-execute":e.execute}}),e._v(" "),a("my-dialog",{attrs:{dialog:e.rejectDialog},on:{"on-dialog-confirm":e.reject,"on-dialog-cancel":function(t){return e.dialogCancel()}}},[a("d-input",{attrs:{type:"textarea",rows:"3",placeholder:"请输入审批意见"},model:{value:e.rejectData.suggestion,callback:function(t){e.$set(e.rejectData,"suggestion",t)},expression:"rejectData.suggestion"}})],1),e._v(" "),a("my-dialog",{attrs:{dialog:e.subscribeProducerEditDialog,visible:"false"},on:{"on-dialog-confirm":function(t){return e.addConfirm()},"on-dialog-cancel":function(t){return e.dialogCancel("subscribeProducerEditDialog")}}},[a("subscribe-producer-apply-form",{attrs:{data:e.subscribeProducerEditData,formType:e.$store.getters.editFormType},on:{"on-dialog-cancel":function(t){return e.dialogCancel("subscribeProducerEditDialog")}}})],1),e._v(" "),a("my-dialog",{attrs:{dialog:e.subscribeConsumerEditDialog,visible:"false"},on:{"on-dialog-confirm":function(t){return e.addConfirm()},"on-dialog-cancel":function(t){return e.dialogCancel("subscribeConsumerEditDialog")}}},[a("subscribe-consumer-apply-form",{attrs:{data:e.subscribeConsumerEditData,formType:e.$store.getters.editFormType},on:{"on-dialog-cancel":function(t){return e.dialogCancel("subscribeConsumerEditDialog")}}})],1),e._v(" "),a("my-dialog",{attrs:{dialog:e.cancelSubscribeEditDialog,visible:"false"},on:{"on-dialog-confirm":function(t){return e.addConfirm()},"on-dialog-cancel":function(t){return e.dialogCancel("cancelSubscribeEditDialog")}}},[a("cancel-subscribe-apply-form",{attrs:{data:e.cancelSubscribeEditData,formType:e.$store.getters.editFormType},on:{"on-dialog-cancel":function(t){return e.dialogCancel("cancelSubscribeEditDialog")}}})],1)],1)},staticRenderFns:[]};var h=a("VU/8")(g,y,!1,function(e){a("gHBM")},"data-v-5a0df5ed",null);t.default=h.exports},gHBM:function(e,t){}});
//# sourceMappingURL=37.11c1a57bcf9069f456ad.js.map