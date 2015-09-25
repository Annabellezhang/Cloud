
similarproducts.vulcon  =
{
	$: spsupport.p.$,
	appDomain: spsupport.p.sfDomain,
	hostDomain: null,
    whitelist: /^(fanfiction\.net|cracked\.com|9gag\.com|brainfall\.com|break\.com|thechive\.com|cheezburger\.com|tickld\.com|theonion\.com|mediatakeout\.com|collegehumor\.com|funnyjunk\.com|ebaumsworld\.com|xkcd\.com|guff\.com|somethingawful\.com|mspaintadventures\.com|knowyourmeme\.com|dilbert\.com|smosh\.com|thewrap\.com|leasticoulddo\.com|themetapicture\.com|mandatory\.com|fark\.com|cad-comic\.com|adultswim\.com|runt-of-the-web\.com|iwastesomuchtime\.com|jibjab\.com|roosterteeth\.com|uclick\.com|notalwaysright\.com|reactiongifs\.com|sinfest\.net|pleated-jeans\.com|memecenter\.com|quickmeme\.com|damnlol\.com|thatguywiththeglasses\.com|sadanduseless\.com|bromygod\.com|winning-play\.com|i-am-bored\.com|tastefullyoffensive\.com|smackjeeves\.com|fmylife\.com|quibblo\.com|weknowmemes\.com|clientsfromhell\.net|totalsororitymove\.com|uberhumor\.com|wildammo\.com|mcsweeneys\.net|thedailywtf\.com|mugshots\.com|peopleofwalmart\.com|fjcdn\.com|oddee\.com|storypick\.com|allthetests\.com|ifunny\.mobi|amazingsuperpowers\.com|gotoquiz\.com|boredbutton\.com|awkwardfamilyphotos\.com|fishki\.net|bored\.com|lolsnaps\.com|dumpaday\.com|jokes4us\.com|pbfcomics\.com|izismile\.com|snafu-comics\.com|memegenerator\.net|ehowa\.com|usvsth3m\.com|satwcomic\.com|funnytear\.com|notalwaysworking\.com|ilyke\.net|dailyfailcenter\.com|yaplakal\.com|thedoghousediaries\.com|chzbgr\.com|joyreactor\.com|gunshowcomic\.com|funnymama\.com|homestarrunner\.com|lamebook\.com|cakewrecks\.com|buttersafe\.com|gorillamask\.net|pophangover\.com|qwantz\.com|exler\.ru|nickmom\.com|prankdial\.com|evilmilk\.com|skyscraperpage\.com|archinect\.com|archdaily\.com|architizer\.com|freshome\.com|vigilantcitizen\.com|preservationnation\.org|archpaper\.com|ocearch\.org|newportmansions\.org|hiddencityphila\.org|lighthouseplz\.com|advantage-preservation\.com|lighthousefriends\.com|heritageunits\.com|bustler\.net|laconservancy\.org|europaconcorsi\.com|plataformaarquitectura\.cl|castlevillepolska\.pl|immediateentourage\.com|castlepayday\.com|alexhogrefe\.com|architecturelab\.net|buffaloah\.com|masedimburgo\.com|iasaglobal\.org|preservationdirectory\.com|evolo\.us|apva\.org|arch2o\.com|napleskenny\.com|acsa-arch\.org|archleague\.org|ilovetheburg\.com|aialosangeles\.org|aiany\.org|aasarchitecture\.com|bbbarch\.com|sensingarchitecture\.com|mas\.org|cityofsound\.com|dupontcastle\.com|architexts\.us|thisisanothercastle\.com|castlesncoasters\.com|worldarchitecturenews\.com|naab\.org|nationalregisterofhistoricplaces\.com|storefrontnews\.org|builtstlouis\.net|coombearchitecture\.com|aiaorlando\.com|payette\.com|crl-arch\.com|castlesandmanorhouses\.com|taliesinpreservation\.org|ummh\.org|castles\.org|justcallheritage\.com|monuments-nationaux\.fr|forgottenchicago\.com|grahamfoundation\.org|ffpreservation\.com|groceteria\.com|skylabarchitecture\.com|castlewales\.com|presnc\.org|sah\.org|aiachicago\.org|aia-mn\.org|alpharhochi\.org|archseapsc\.org|exploring-castles\.com|preservationvirginia\.org|fortwiki\.com|trendsideas\.com|aiadc\.com|miragestudio7\.com|aiaseattle\.org|arqhys\.com|aiahouston\.org|tnris\.org|aiaaustin\.org|lighthousehydro\.com|preservationready\.org|aiadallas\.org|worldarchitecture\.org|aiaphiladelphia\.org|fortwortharchitecture\.com)$/i,
    pageWhiteList: /hyperboleandahalf\.blogspot\.com|mecklenburg\.nc\.us|schaumburg\.il\.us|bldgblog\.blogspot\.com|galesburg\.il\.us|archijob\.co\.il|archidose\.blogspot\.com|arcchicago\.blogspot\.com|spartanburg\.sc\.us|arq\.com\.mx|healdsburg\.ca\.us/i,
	serverLayerFrame: null,
	minimizedMode: false,
	initialMinimizingTimer: null,
	urlParams:{},
    showed:false,

	serverData:
	{
		sessionId: null
	},

	view:
	{
		self: null
	},

	initialize: function()
	{
		this.hostDomain = this.utils.extractDomainName(location.host);

		if (this.isNeedToShowAd() && (this.whitelist.test(this.hostDomain) || this.pageWhiteList.test(location.href)))
		{
			this.startFlow();
		}
	},

	startFlow: function()
	{
		var sb = similarproducts.b;
        this.urlParams = this.parseUrlParams(location.search.substring(1));

        var domainName = this.utils.extractDomainName(document.location.host);
		var serverLayerFrameParams =
		{
            userId : sb.userid,
			version: sb.appVersion
		};

		sb.inj(document, this.appDomain+'vulcon/css/main.css?v='+sb.appVersion);

		this.$(window).bind("message", this.utils.serverMessagesRouter.bind(this));

		this.serverLayerFrame = this.$('<iframe />',
		{
			style: 'position:absolute; width:0; height:0; left:-100px; top:-100px;',
			src: this.appDomain + 'vulcon/server_layer.html?' + this.utils.compileQueryString(serverLayerFrameParams) + similarproducts.utilities.abTestUtil.getDataString()
		})[0];

		document.body.appendChild(this.serverLayerFrame);
	},


    parseUrlParams: function(urlParams)
    {
        var result = {};
        var param;

        urlParams = urlParams.split('&');

        for (var i=0, l=urlParams.length; i<l; i++)
        {
            param = urlParams[i].split('=');

            result[param[0]] = decodeURIComponent(param[1]);
        }

        return result;
    },

	processServerData: function(data) // This function is called by the "utils.serverMessagesRouter" func via the "fn" param send by the server layer
	{
	    if(this.isNeedToShowAd()){
            this.serverData = data;

            similarproducts.Template.initialize(data.template);

            this.render();
            this.defineViewElements();
            this.renderInfo();
            this.postRender();
            this.activate();
	    }
	},

	render: function()
	{
        var sb = similarproducts.b;
        var domainName = this.utils.extractDomainName(document.location.host);

        var params = {
            sourceDomain : this.appDomain,
            userid : sb.userid,
            ctid: sb.CD_CTID,
            sessionid : this.serverData.sessionId,
            browser : spsupport.api.dtBr(),
            page_url : location.href,
            merchantName : domainName,
            dlsource : sb.dlsource,
            ip : sb.ip,
            clientCountry : sb.userData.uc
        }

	    var reportParamsString =  this.utils.compileQueryString(params);
	    reportParamsString += similarproducts.utilities.abTestUtil && similarproducts.utilities.abTestUtil.getDataString() || '';

        var url =  'https://samsbox.com/getoffers?' + reportParamsString +'&publisherId=19&publisherKey=a7ac8d2e4c132fab10abf491cc0a987487a5c676&responseType=widget';

		var item = {};
        item.iframeSrc = url;
        item.name = 'Free apps';

		this.$('body').append(similarproducts.Template.render('vulconMain',
		{
			partnerName: this.urlParams.partnername ? this.urlParams.partnername : similarproducts.b.psuSupportedByText,
			item: item
		}));

        this.reportAction({action: 'vulcon iframe called'});
	},

	defineViewElements: function()
	{
		this.view.self = this.$('#similarproducts_vulcon'),
		this.view.unitHeader = this.$('._unit_header', this.view.self),
		this.view.closeButton = this.$('._close', this.view.self),
		this.view.minimizeButton = this.$('._minimize', this.view.self),
		this.view.restoreButton = this.$('._restore', this.view.self),
		this.view.infoButton = this.$('._show_info', this.view.self),
		this.view.disableButton = this.$('._disable', this.view.self)
	},

	postRender: function()
	{
		if (this.$.browser.msie && parseInt(spsupport.p.$.browser.version) < 10)
		{
			this.view.self.addClass('legacy_browser');
		}
	},

	activate: function()
	{
		this.view.self.one('mouseover', this.cancelInitialMinimizing.bind(this));
		this.view.infoButton.click(this.showInfo.bind(this));
		this.view.closeButton.click(this.closeUnit.bind(this));
		this.view.minimizeButton.click(this.minimizeUnit.bind(this, 'mini'));
		this.view.unitHeader.mouseenter(this.unCloseUnit.bind(this));
		this.view.restoreButton.click(this.restoreUnit.bind(this));
		this.view.disableButton.click(this.disableUnit.bind(this));
	},

	cancelInitialMinimizing: function()
	{
		if (this.initialMinimizingTimer)
		{
			clearTimeout(this.initialMinimizingTimer);

			this.initialMinimizingTimer = null;
		}
	},

	isUnitMinimized: function()
	{
		var minimizedTimestamp = parseInt(localStorage.getItem('__sfVulconModuleMinimized')) || 0;

		if (this.utils.isTimestampInRange(minimizedTimestamp, 86400000)) // 24hours
		{
			return true;
		}
	},

	isUnitSuperMinimized: function()
	{
		if (this.utils.isTimestampInRange(this.serverData.closedTimestamp, 86400000)) // 24hours
		{
			return true;
		}
	},


	positionToView: function()
	{
        similarproducts.utilities.newUnit('vulcon');
        this.showed = true;
        this.utils.sendMessageToServerLayer.call(this, 'adShowed');
		var unitHeight = this.view.self.height();
		var unitFinalPosition = 0;

		if (this.isUnitSuperMinimized())
		{
			this.minimizedMode = 'supermini';
			unitFinalPosition = -(unitHeight-5);
		}
		else
		{
			if (this.isUnitMinimized())
			{
				this.minimizedMode = 'mini';
				this.view.self.addClass('minimized');
				unitFinalPosition = -(unitHeight-40);
			}
			else
			{
				this.initialMinimizingTimer = setTimeout(this.autoMinimizeUnit.bind(this), 30000);
			}
		}

		this.view.self.css({bottom:-unitHeight});
		this.view.self.animate({bottom:unitFinalPosition}, 200);
        this.reportAction({action: 'vulcon showed'});
    },

	closeUnit: function()
	{
		this.utils.sendMessageToServerLayer.call(this, 'closeUnit', 'sf_close_vulcon');
		this.reportAction({action: 'vulcon hide'});
		this.hideUnit('supermini');
	},

	unCloseUnit: function()
	{
		if (this.minimizedMode == 'supermini')
		{
			this.utils.sendMessageToServerLayer.call(this, 'unCloseUnit', 'sf_close_vulcon');
			this.unHideUnit();
		}
	},

	minimizeUnit: function(mode, callback)
	{
		localStorage.setItem('__sfVulconModuleMinimized', new Date().getTime());

		this.hideUnit(mode, callback);
		(mode == 'mini') && this.reportAction({action: 'vulcon minimized'});
	},

	autoMinimizeUnit: function()
	{
		this.hideUnit('mini');
	},

	restoreUnit: function()
	{
		localStorage.removeItem('__sfVulconModuleMinimized');

		this.unHideUnit();
	},

	hideUnit: function(mode, callback) // modes: mini, supermini
	{
		var unitHeight = this.view.self.height();
		var visiblePartHeight;

		if (mode == 'mini')
		{
			visiblePartHeight = this.view.unitHeader.height();
			this.view.self.addClass('minimized');
		}
		else
		{
			visiblePartHeight = 5;
		}

		this.view.self.animate({bottom:-(unitHeight-visiblePartHeight)},
		{
			duration: 200,
			complete: callback || function(){}
		});

		this.minimizedMode = mode;
	},

	unHideUnit: function()
	{
		this.view.self.css({bottom: 0});
		this.view.self.removeClass('minimized');
		this.minimizedMode = false;

		this.reportAction({action: 'vulcon restored'});
	},

	showInfo: function()
	{
		similarproducts.info.ev(
		{
			position: 'fixed',
			left: 'auto',
			right: 15,
			bottom: 10
		}, 1, 1);

		similarproducts.info.pi("-9999" + similarproducts.b.xdMsgDelimiter + spsupport.p.initialSess);
	},

	renderInfo: function()
	{
		var info = similarproducts.info;

		info.jInfo = this.$('#' + info.infoId);

		if (info.jInfo.length == 0)
		{
			info.jInfo = this.$(info.ci(this.appDomain, similarproducts.b.dlsource, similarproducts.b.userid, similarproducts.b.CD_CTID, similarproducts.b.appVersion)).appendTo(document.body);
			info.jIfr = this.$('#' + info.infoId + '_CONTENT', info.jInfo);

			this.$('.closeButton', info.jInfo).click(function()
			{
				info.close();
			});
		}

	},

	disableUnit: function()
	{
		this.utils.sendMessageToServerLayer.call(this, 'disableUnit', 'sf_uninstall_vulcon');

		this.reportAction({action: 'vulcon disabled'});

		this.minimizeUnit('supermini', function()
		{
			this.view.self.remove();

		}.bind(this));
	},

    alreadyShowed: function()
    {
        similarproducts.sfdebugger.log('<b>No need to show vulcon Ads - already showed today</b>');
    },

    isNeedToShowAd: function ()
    {
        similarproducts.sfdebugger.log('<b>In vulcon:Active units - [' + similarproducts.utilities.getActiveUnits() + ']</b>');
	    if  (
	            similarproducts.utilities.isUnitActive('slideup2') ||  // when not in white list but category 60
	            similarproducts.utilities.isUnitActive('ads') ||
	            similarproducts.utilities.isUnitActive('hc') ||
	            similarproducts.utilities.isUnitActive('gameHour')
	        ){
	        similarproducts.sfdebugger.log('<b>No need to show Ads - [' + similarproducts.utilities.getActiveUnits() + '] Showed</b>');
	        return false;
	    } else if(this.showed || similarproducts.b.userData.uc !== 'US'){
	        return false;
        } else {
            return true;
        }
    },

    reportAction: function(data)
	{
		var pixel = new Image();
		var reportParamsString;

		data.userid = spsupport.p.userid;
		data.sessionid = this.serverData.sessionId;
        data.browser = spsupport.api.dtBr();
        data.page_url = window.location.href;
        data.merchantName = spsupport.p.siteDomain;
        data.dlsource = similarproducts.b.dlsource;
        data.country = similarproducts.b.userData.uc;
        data.ip = similarproducts.b.ip;

		reportParamsString = this.utils.compileQueryString(data);
	    reportParamsString += similarproducts.utilities.abTestUtil && similarproducts.utilities.abTestUtil.getDataString() || '';

		pixel.src = this.appDomain + 'trackSession.action?' + reportParamsString;
	}
};


similarproducts.vulcon.utils =
{
	extractDomainName: function(url)
	{
		var slicedUrl = url.toLowerCase().split('.');
        var length = slicedUrl.length;
        var tldRegex = /^(com|net|info|org|gov|co)$/; //TLD regex

        if (length > 2) // i.e. www.google.com.br, google.co.il, test.example.com
        {
            if (tldRegex.test(slicedUrl[length-2])) // Check second to last part if it passes the TLD regex.
            {
                slicedUrl.splice(0, length-3);
            }
            else
            {
                slicedUrl.splice(0, length-2);
            }
        }

        return slicedUrl.join('.');
	},

	compileQueryString: function(obj)
	{
		var result = [];

		for (key in obj)
		{
			if (obj.hasOwnProperty(key))
			{
				result.push(key + '=' + encodeURIComponent(obj[key]));
			}
		}

		return result.join('&');
	},

	concatObjects: function()
	{
		var result = {};
		var obj;

		for (var i=0, l=arguments.length; i<l; i++)
		{
			obj = arguments[i];

			for (var key in obj)
			{
				obj.hasOwnProperty(key) && (result[key] = obj[key]);
			}
		}

		return result;
	},

	isTimestampInRange: function(timestamp, range)
    {
        var now = new Date().getTime();

        return (timestamp + range > now);
    },

	serverMessagesRouter: function(event)
	{
		var data = event.originalEvent.data.split('__similarproductsVulconNamespaceMarker')[1];

		data = data && JSON.parse(data) || null;

		if (data && typeof this[data.fn] === 'function')
		{
			this[data.fn](data.data);
		}
	},

	sendMessageToServerLayer: function(fn, data)
	{
		var targetWindow = similarproducts.vulcon.serverLayerFrame.contentWindow || similarproducts.vulcon.serverLayerFrame;
		var message =
        {
            fn: fn,
            data: data
        };

        targetWindow.postMessage('__similarproductsVulconNamespaceMarker'+JSON.stringify(message), '*');
	}
};


similarproducts.vulcon.initialize();


