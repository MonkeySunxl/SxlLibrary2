# SxlLibrary
项目包括 
    国际号 
    版本更新 
    各种Utils 
    base类
 1:   
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

2:
	dependencies {
	        compile 'com.github.MonkeySunxl:SxlLibrary:2.1'
	}
  
  版本更新用法:
      new CheckVersion(context,newVersion,downLoadUrl);
    
  国际号:获取界面跳转到CountryActivity  选择国际号,选择完  在获取界面  
  @Override
    protected void onResume() {
        super.onResume();
	//Config.COUNTRYNUMBER
        tvSelectCity.setText(Config.COUNTRYNUMBER);
    }  
