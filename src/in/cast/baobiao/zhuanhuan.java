package in.cast.baobiao;

import java.util.List;

public class zhuanhuan {
      
	public void zhuanhuan_ALL(List<String> xinxi_thing, List<String> kuaijiANDcaiwu_thing, List<String> dongshihuibaogao_thing, List<String> xiaoshou_thing, List<String> gongying_thing){
		
		new zhuanhuan().xinxi_thing_zhuanhuan(xinxi_thing);
		new zhuanhuan().kuaijiANDcaiwu_thing_zhuanhuan(kuaijiANDcaiwu_thing);
		new zhuanhuan().dongshihuibaogao_thing_zhuanhuan(dongshihuibaogao_thing);
		//new zhuanhuan().xiaoshou_thing_zhuanhuan(xiaoshou_thing);
		//new zhuanhuan().gongying_thing_zhuanhuan(gongying_thing);
	}

	private void gongying_thing_zhuanhuan(List<String> gongying_thing) {
		// TODO Auto-generated method stub
		
	}

	private void xiaoshou_thing_zhuanhuan(List<String> xiaoshou_thing) {
		// TODO Auto-generated method stub
		
	}

	private void dongshihuibaogao_thing_zhuanhuan(List<String> dongshihuibaogao_thing) {
		// TODO Auto-generated method stub
		String kuaijiANDcaiwu[]={"yingyechengben","yingyefenyong","xiaoshoufenyong","guanlifeiyong",
				"cawufenyong","suodeishuifeiyong","yingyelirun","jinglirun","yanfazhichu",
				
				"jingyinghuodongxianjinliuruxiaoji","jingyinghuodongxianjinliuchuxiaoji","daozihuodongxianjinliuruxiaoji",
				"jingyinghuodongchanshengdexianjinliuliangjinge","touzihuodongchanshengdexianjinliuliangjinge",
				
				"chouzihuodongxianjinliuruxiaoji","chozihuodongliuchuxiaoji","chouzihuodongchanshengdexianjinliuliangjinge",
				"xianjianjixianjindengjianwujinzengjiae","huobizijin","yingshoupiaoju","yingshouzhangkuan","chunhuo",
				
				"changqiguquantouzi","gudingzichan","zaijiangongcheng","changqijiekuan","duanqijiekuan",
				
				"qitaliudongzijin","changqidaitanfeiyong","qitafeiliudongzichan","yingfuzhangkuan","yushouzhangkuan",
				"yingfulixi","yingfuguli","lingfuzhaiquan","qitafeiliudongfuzhai",
				
				"zibengongji","zhuanxiangchubei","yingyugongji","shaoshugudongquanyi",
				
				"dongshihuiguanyugongshibaogaoqineijingyingqingkuangdetaolunyufenxi","hexinjingzhengli",
		        };
		for(int i=0;i<dongshihuibaogao_thing.size();i++){
			if(i==dongshihuibaogao_thing.size()-1||i==dongshihuibaogao_thing.size()-2){
				dongshihuibaogao_thing.set(i, kuaijiANDcaiwu[i]+":"+dongshihuibaogao_thing.get(i));
			}else{
				String fenjie=dongshihuibaogao_thing.get(i).split(":")[1];
			    dongshihuibaogao_thing.set(i, kuaijiANDcaiwu[i]+":"+fenjie);
			}
			
		}
	}

	private void kuaijiANDcaiwu_thing_zhuanhuan(List<String> kuaijiANDcaiwu_thing) {
		// TODO Auto-generated method stub
		String kuaijiANDcaiwu[]={"yingyeshouru","guishushangshigongsidejinglirun","guishushangshigongsidekouchufeijingchangxingsunyijinlirun",
				"jingyinghuodongchanshengdexinjinliuliangjinge","jibenmeigushouyi","xishimeigushouyi",
				"gushushanghsigongsigudongdejinglirun","zongzichan"
		        };
		for(int i=0;i<kuaijiANDcaiwu_thing.size();i++){
			String fenjie=kuaijiANDcaiwu_thing.get(i).split(":")[1];
			kuaijiANDcaiwu_thing.set(i, kuaijiANDcaiwu[i]+":"+fenjie);
		}
	}

	private void xinxi_thing_zhuanhuan(List<String> xinxi_thing) {
		// TODO Auto-generated method stub
		for(int i=0;i<xinxi_thing.size();i++){
			String fenjie=xinxi_thing.get(i).split(":")[1];
			xinxi_thing.set(i, fenjie);
		}
	}
}
