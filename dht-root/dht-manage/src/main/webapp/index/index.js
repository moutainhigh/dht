/*
	主页加载方法
	@eric
*/
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	view: {
		selectedMulti: false
	},
	callback: {
		onClick:function(e, id, node){
			var zTree = $.fn.zTree.getZTreeObj("menuTree");
			if(node.isParent) {
				zTree.expandNode();
			} else {
				addTabs(node.name, node.file);
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"系统管理", open:true},
	{ id:11, pId:1, name:"用户管理", file:"user/user.html"},
	{ id:12, pId:1, name:"数据备份", file:"backup.html"},
	{ id:13, pId:1, name:"权限管理", file:"authority.html"},
	{ id:14, pId:1, name:"角色管理", file:"role.html"},
	{ id:2, pId:0, name:"父节点", open:true},
	{ id:21, pId:2, name:"子节点21", file:""},
	{ id:22, pId:2, name:"子节点22", file:""},
	{ id:23, pId:2, name:"子节点23", file:""}
];

$(function() {
	$.fn.zTree.init($("#menuTree"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	
	//中间部分tab
	$('#tabs').tabs({  
		border:false,
		fit:true,
		onSelect: function(title, index){
			var treeNode = zTree.getNodeByParam("name", title, null);
			zTree.selectNode(treeNode);
		}
	}); 
	
	$('.index_panel').panel({  
	  width:300,  
	  height:200,  
	  closable:true,
	  minimizable:true,
	  title: 'My Panel'
	});
	
});

