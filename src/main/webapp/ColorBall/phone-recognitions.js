var MobileDetect = {
    dedected:false,
    OS:{
        name:'not fount',
        version: 0,
        majorVersion: 0
    },
    detect:function(){
        if (!this.dedected) {
            var vTemp;
            for (var sPhone in this.m_oOsRegexs) {
                try {
                    if (this.m_oOsRegexs[sPhone].regs.check.test(navigator.userAgent)) {
                        this.OS.name = sPhone;
                        vTemp = this.m_oOsRegexs[sPhone].regs.version.exec(navigator.userAgent);
                        if (vTemp[this.m_oOsRegexs[sPhone].regs.versionKey]) {
                            this.OS.version = vTemp[this.m_oOsRegexs[sPhone].regs.versionKey];
                        }
                        vTemp = this.m_oOsRegexs[sPhone].regs.majorVersion.exec(navigator.userAgent);
                        if (vTemp[this.m_oOsRegexs[sPhone].regs.versionKey]) {
                            this.OS.majorVersion = vTemp[this.m_oOsRegexs[sPhone].regs.versionKey];
                        }
                        this.dedected = true;
                        break;
                    }
                }catch(oError){}
            }
        }
        return this;
    },
    updateEnvironment:function(){
        this.detect();
        this.setSpecailHeaders();   
        this.setClasses();
        this.setSpecialScripts();
    },
    setClasses:function(){
        this.detect();
        //add classes
        var oBody = document.getElementsByTagName('body')[0];
        var sClasses = '';
        //old classes
        if(oBody.getAttribute('class')){
            sClasses += oBody.getAttribute('class') + ' ';
        }
        //add os
        if(this.OS.name){
            sClasses += 'os-' + this.OS.name.toLowerCase() + ' ';
        }
        //add os version
        if(this.OS.version){
            sClasses += 'osv-' + this.OS.version.toLowerCase().replace(/\W/g, '_')  + ' ';
        }
        //add os major version
        if(this.OS.majorVersion){
            sClasses += 'osmv-' + this.OS.majorVersion.toLowerCase().replace(/\W/g, '_');
        }
        //add classes
        oBody.setAttribute('class', sClasses);          
    },
    setSpecailHeaders: function(){
        this.detect();
        var oHeader = document.getElementsByTagName('head')[0];
        var oTempElement;
        if(this.OS.name == 'ipod' || this.OS.name == 'iphone' || this.OS.name == 'ipad' || this.OS.name == 'android'){
            //viewport
            oTempElement = document.createElement('meta');
            oTempElement.setAttribute('name','viewport');
            oTempElement.setAttribute('content', "width=device-width,maximum-scale=1.0, initial-scale=1.0, user-scalable=no");
            oHeader.appendChild(oTempElement);
            //apple-mobile-web-app-capable
            oTempElement = document.createElement('meta');
            oTempElement.setAttribute('name','apple-mobile-web-app-capable');
            oTempElement.setAttribute('content', "yes");
            oHeader.appendChild(oTempElement);
        }
    },
    setSpecialScripts: function(){
        if (this.OS.name == 'ipod' || this.OS.name == 'iphone' || this.OS.name == 'android') {
            addEventListener("load", function(){
                setTimeout(function(){
                    window.scrollTo(0, 1);
                }, 0);
            }, false);
        }
    },
    m_oOsRegexs:{
        ipod:{ 
            regs:{
                check: /iPod/i,
                version: /OS\s+([0-9_\.-]+)?/i,
                majorVersion: /OS\s+([0-9]{1})?/i,
                versionKey: 1
            }
        },      
        iphone:{ 
            regs:{
                check: /iPhone/i,
                version: /OS\s+([0-9_\.-]+)?/i,
                majorVersion: /OS\s+([0-9]{1})?/i,
                versionKey: 1
            }
        },
        ipad:{ 
            regs:{
                check: /iPad/i,
                version: /OS\s+([0-9_\.-]+)?/i,
                majorVersion: /OS\s+([0-9]{1,})?/i,
                versionKey: 1
            }
        },
        macintosh:{ 
            regs:{
                check: /mac/i,
                version: /OS\s+([a-z]+\s{0,}[0-9_\.-]+)?/i,
                majorVersion: /OS\s+([a-z]+\s{0,}[0-9]{1,})?/i,
                versionKey: 1
            }
        },
        windows:{
            regs:{
                check: /Windows/i,
                version: /Windows\s+NT\s+([0-9\.]+)?/i,
                majorVersion: /Windows\s+NT\s+([0-9]{1,})?/i,
                versionKey: 1
            }
        },
        android:{
            regs:{
                check: /android/i,
                version: /android\s([0-9.]+)?/i,
                majorVersion: /android\s([0-9]{1,})?/i,
                versionKey: 1
            }           
        },
        symbian:{
            regs:{
                check: /symbian/i,
                version: /SymbianOS\/([0-9.]+)?/i,
                majorVersion: /SymbianOS\/([0-9]{1,})?/i,
                versionKey: 1
            }           
        }       
                
    }
}