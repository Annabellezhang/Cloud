similarproducts.ProductAds  =
{
	$: spsupport.p.$,
	appDomain: spsupport.p.sfDomain,
	hostDomain: '',
	initialized:false,
	serverLayerFrame: null,
	minimizedMode: false,
	initialMinimizingTimer: null,
	banner: null,
    showed:false,

	reportData:
	{
		siteType: [],
		foundTerms: []
	},

	serverData: {},
	view: {},

	flows:
	{
		petMatch:
		{
			prefix: 'petmatch',
			whiteList: /^(bluebuffalo\.com|petcarerx\.com|dinovite\.com|malwarebytes\.org|onlinecourseproviders\.com|theshelterpetproject\.org|cuteoverload\.com|petflow\.com|dailypuppy\.com|jefferspet\.com|animalplanet\.com|companiondogboarding\.com|waghotels\.com|smilindogs\.com|freefunnydogpictures\.com|tehcute\.com|happyhound\.biz|k9kulture\.com|funnycatsite\.com|funnycatpix\.com|lolcats\.com|talking-dogs\.com|talkingdogstudios\.com|roflcat\.com|tips-for-cats\.com|amazingcatcollection\.com|cuteness\.com|puppynames\.com|dogbreedinfo\.com|mydogsname\.com|vetstreet\.com|smalldogplace\.com|gopetplan\.com|alldognames\.com|lovemeow\.com|chewy\.com|catnamesmeow\.com|catchannel\.com|tica\.org|cfa\.org|petmedsonline\.org|nextdaypets\.com|puppyfind\.com|mnn\.com|adoptapet\.com|petfinder\.com|poundpuppyrescue\.org|dpsrescue\.org|hssv\.org|sfspca\.org|spcamc\.org|aspca\.org|spcaonline\.com|hshv\.org|allpaws\.com|adoptapuppyforfree\.com|furryfriendsrescue\.org|petsunlimited\.org|paloaltohumane\.org|peninsulahumanesociety\.org|animalshelter\.org|animalhumanesociety\.org|rocketdogrescue\.org|communitycatrescue\.org|akc\.org|azhumane\.org|michiganhumane\.org|petango\.com|adoptapetfenton\.com|paws\.org|adoptpetshelter\.org|foundanimals\.org|bestfriends\.org|theshelterpetproject\.org|peninsulacatworks\.org|kittenrescue\.org|ninelivesfoundation\.org|humanimalconnection\.com|bayareacatrescue\.org|cap4pets\.org|petharbor\.com|thenoahcenter\.org|homewardpet\.org|animaladoption\.com|animaladoptioncenter\.org|theshelterpetproject\.org|stuffonmymutt\.com|aplacetolovedogs\.com|petadoptionnetwork\.org|petango\.com|bayareapetadoptions\.org|dpsrescue\.org|petsadoption\.com|petsmart\.com|petfooddirect\.com|petco\.com|bluebuffalo\.com|petfoodexpress\.com|petflow\.com|wag\.com|pet360\.com|purina\.com|drsfostersmith\.com|petsupplies\.com|petfooddepot\.com|petsuppliesplus\.com|petedge\.com|petmountain\.com|jefferspet\.com|jjdog\.com|petsugar\.com|funnydogsite\.com|cuteanimalsonline\.com|cutestpaw\.com|dailypuppy\.com|tehcute\.com|thedailycute\.com|petoftheday\.com|attackofthecute\.com|cutearoo\.com|cuteoverload\.com|cutestpetever\.com|justcuteanimals\.com|lolcuteanimals\.com|cutestpetcontest\.com|dogfoodadvisor\.com|dogfoodanalysis\.com|petfoodhonesty\.com|pets\.webmd\.com|petmd\.com|dogingtonpost\.com|entirelypets\.com|petage\.com|lifewithdogs\.tv|pawnation\.com|dogtime\.com|cesarsway\.com|puppyintraining\.com|dogster\.com|petsmartcharities\.org|1800petmeds\.com|petcarerx\.com|petadoptionfund\.org|doggylamapetcare\.com|foundanimals\.org|theshelterpetproject\.org|petfoodexpress\.com|petsinneed\.com|animal-league\.org|adoptapet\.com|perfectpetmatch\.com|akc\.org|iams\.com|royalcanin\.com|pedigree\.com|thepetclub\.net|bestfriends\.org|purina\.com|americanhumane\.org|onlynaturalpet\.com|dinovite\.com|petsolutions\.com|dogsupplies\.com|petsupplies\.com|callingalldogs\.com)$/i,
			pageWhiteList: /craigslist\.org\/(.+\/)?(pet|pas|laf)|.+\.craigslist\.org\/pet|.+\.craigslist\.org\/search\/pet|petstew\.com\/index\.html|petadoption\.ocpetinfo\.com\/adopt|animall\.org\/adopt|sfbay\.craigslist\.org\/(pas|pet)|sfbay\.craigslist\.org\/(pen|sfc|sby|eby|nby|scz)\/pet|walmart\.com\/cp\/pet-supplies\/5440|tractorsupply\.com\/en\/store\/pet|ebay\.com\/chp\/(pet-supplies|dog-supplies-\/20742|cat-supplies-\/20737)|pinterest\.com\/all\/animals|photobucket\.com\/images\/dog\?page=1|dogperday\.com\/gallery\/index\.php\?cat_id=1|reddit\.com\/r\/aww|imgur\.com\/r\/(pets|cats|dogs)|shine\.yahoo\.com\/blogs\/pets|animalplanet\.com\/pets|ellentv\.com\/pets|peoplepets\.com\/people\/pets|today\.com\/pets|sheknows\.com\/pets-and-animals|selectsmart\.com\/(dog|cat)|reallycutepets\.wordpress\.com|(dogs|cats)\.lovetoknow\.com|healthypets\.mercola\.com|pets\.overstock\.com|petsearch\.animalhumanenm\.org|adopt\.maddiesfund\.org|hssv\.convio\.net/i,
			terms: /pet adoptions|pet clinic|pet adoption|pet rescue|animal service center|animal services|animal rights|animal shelter|spca|adopt a pet|dogs adoption|cats adoption/gim,
			banners:
			[
			    {bannerName: "L1", landingPage: "http://www.superfish.com/petmatch/?utm_source=WSInsite&utm_medium=Banner&utm_content=bannerL1&utm_campaign=PMInsite", showButtons: true}
		    ]
		},
		decor:
		{
			prefix: 'decor',
			whiteList: /^(101cookbooks\.com|123-free-download\.com|2000shareware\.com|2modern\.com|3d2f\.com|411homerepair\.com|5tipsto\.com|ab-archive\.net|abcdatos\.com|abeautifulmess\.com|about\.com|acozykitchen\.com|addicted2decorating\.com|afreego\.com|afwonline\.com|albydapress\.com|alldaychic\.com|allrecipes\.com|allthingsthrifty\.com|amazinginteriordesign\.com|amazingribs\.com|amberinteriordesign\.com|americanwoodmark\.com|amodelrecommends\.com|amuse-your-bouche\.com|ana-white\.com|annstreetstudio\.com|answers\.com|anthro\.com|aol\.com|apartment34\.com|apartmentguide\.com|apartments\.com|apartmenttherapy\.com|applianceaid\.com|apponic\.com|apt2b\.com|archello\.com|archibaseplanet\.com|archilovers\.com|architecturaldesigns\.com|architecturaldigest\.com|architizer\.com|ask\.com|askthebuilder\.com|asp-shareware\.org|associerge\.com|atlantatile\.com|backhomemagazine\.com|bagsnob\.com|baidu\.com|becauseimaddicted\.net|bestvistadownloads\.com|betterlivingthroughdesign\.com|bettycrocker\.com|bhg\.com|bloombety\.com|bluechillies\.com|blueprintsocial\.com|bobvila\.com|brightbazaarblog\.com|bryanboy\.com|bugsandbeans\.com|builderonline\.com|buildingconservation\.com|buildzoom\.com|businessoffashion\.com|buyaz\.org|buzzbuzzhome\.com|buzzfeed\.com|cakecentral\.com|campbellskitchen\.com|centsationalgirl\.com|cereusart\.com|charlesandhudson\.com|chasingdelicious\.com|cheftalk\.com|chocolatecoveredkatie\.com|ciiwa\.com|cnet\.com|coastalliving\.com|cobraworkshop\.com|cococozy\.com|cocokelley\.com|cocoontech\.com|collegefashion\.net|construction\.com|consumer-rankings\.org|contemporist\.com|cookieandkate\.com|cookinglight\.com|cooks\.com|cookscountry\.com|cooksillustrated\.com|coolhouseplans\.com|core77\.com|cort\.com|countryliving\.com|countrysidemag\.com|craneandcanopy\.com|createandbabble\.com|createcraftlove\.com|creatingreallyawesomefreethings\.com|creaturecomfortsblog\.com|cupcakesandcashmere\.com|curbed\.com|custommade\.com|dailymotion\.com|dappered\.com|datapicks\.com|davesgarden\.com|debbie-debbiedoos\.com|deckhouse\.com|decomyplace\.com|decor8blog\.com|decorchick\.com|decorpad\.com|decorsalteado\.com|deepnot\.com|defogitall\.com|deliaonline\.com|delineateyourdwelling\.com|design-milk\.com|design-seeds\.com|designbump\.com|designeddecor\.com|designformankind\.com|designkastle\.com|designrulz\.com|designshuffle\.com|designsponge\.com|desiretoinspire\.net|deviantart\.com|dexigner\.com|dezeen\.com|digsdigs\.com|diohomeimprovements\.com|directbuy\.com|diycandy\.com|diychatroom\.com|diydata\.com|diyfixit\.co\.uk|diyinspired\.com|diylife\.com|diynetwork\.com|diynot\.com|diyonline\.com|diyprojectsworld\.com|diyshowoff\.com|docxie\.com|doityourself\.com|domain-home\.com|domainehome\.com|domestically-speaking\.com|domesticimperfection\.com|donotcall\.gov|dornob\.com|down3dmodels\.com|downloadatlas\.com|downloadroute\.com|downloadtyphoon\.com|dreamhomesource\.com|drinksmixer\.com|dwell\.com|dzyngiri\.com|eatingwell\.com|edtaf\.com|elementsofstyleblog\.com|ellecanada\.com|elledecor\.com|emahal\.com|energystar\.gov|epicurious\.com|eplans\.com|evmambo\.com|evtiel\.com|ezpong\.com|fabsugar\.com|faburous\.com|faceplane\.com|familyhandyman\.com|fancy\.com|fashionbeans\.com|fashionbombdaily\.com|fashiongonerogue\.com|fashionising\.com|fashionista\.com|fashionmagazine\.com|fashionnstyle\.com|fashionstylemag\.com|fashiontoast\.com|fashiontv\.com|fashionweekdaily\.com|filedir\.com|fileflash\.com|fileratings\.com|findinghomeonline\.com|findyourfurniture\.com|finecooking\.com|finehomebuilding\.com|fixitnow\.com|flashgolden\.com|flatvsrealism\.com|flickr\.com|floorplanner\.com|floorplans\.com|food\.com|foodandwine\.com|foodgawker\.com|foodnetwork\.com|forsalebyowner\.com|fortikur\.com|foxhollowcottage\.com|foxnews\.com|francotechnogap\.com|freetrademagazines\.com|freshome\.com|funchap\.com|furnacecompare\.com|furnishconnection\.com|furniturefashion\.com|furnituretoday\.com|furnizz\.com|garden\.org|gardeningknowhow\.com|gardenweb\.com|getdecorating\.com|getkempt\.com|getridofthings\.com|gharexpert\.com|giesendesign\.com|glitterglueandpaint\.com|gocadesigns\.com|goodbed\.com|goodhomes-magazine\.com|goodhousekeeping\.com|goodlifeeats\.com|goodshomedesign\.com|gq\.com|graydoor\.com|greenfrontfurniture\.com|groovexi\.com|guatacrazynight\.com|h2obungalow\.com|hadleycourt\.com|hammerzone\.com|handybook\.com|handymanwire\.com|harpersbazaar\.com|hative\.com|hausbauagentur\.com|hautenature\.com|hdsupply\.com|heartifb\.com|heatinghelp\.com|hermanmiller\.com|hersheys\.com|hgtv\.com|hgtvgardens\.com|hgtvremodels\.com|hiddentreasuresdecorandmore\.com|highsnobiety\.com|hivemodern\.com|hkstandard\.com|home-designing\.com|homeadore\.com|homeanddesign\.com|homeandgardencafe\.com|homebuilding\.co\.uk|homebunch\.com|homedesignfind\.com|homedesignlover\.com|homedit\.com|homedsgn\.com|homeenergy\.org|homefinder\.com|homegoods\.com|homeimprove\.com|homeinfatuation\.com|homeinluxury\.com|homemodish\.com|homeornaments\.com|homeplans\.com|homepolish\.com|homeportfolio\.com|homes\.com|homestars\.com|homestead\.org|homestratosphere\.com|homestyler\.com|homestyletips\.com|hometalk\.com|hometime\.com|hometips\.com|hometipsworld\.com|hometoys\.com|hommemaker\.com|homohome\.com|hon\.com|honestlywtf\.com|hookedonhouses\.net|horyaalada\.com|hotpads\.com|houseandhome\.com|housebeautiful\.com|houselogic\.com|houseofearnest\.com|houseofroseblog\.com|houseofturquoise\.com|houseplans\.com|houserepairtalk\.com|housetweaking\.com|houzz\.com|howdens\.com|howtocleanstuff\.net|howtodothings\.com|howtomendit\.com|hpmirror\.com|https\.com|huffingtonpost\.com|hvac-talk\.com|idealhomegarden\.com|iida\.org|ikeafamilylivemagazine\.com|ikeafans\.com|imagefs\.com|imgur\.com|imnewswatch\.com|improvenet\.com|indulgy\.com|inhabitat\.com|inmyownstyle\.com|insofter\.com|inspectapedia\.com|inspirationalbum\.com|instagram\.com|instructables\.com|interiorcollective\.com|interiordesign\.net|interiordesignable\.com|interiordesignshow\.com|interiordezine\.com|interiorholic\.com|interiorsdigital\.com|inthralld\.com|intothegloss\.com|iwdownload\.com|jamieoliver\.com|jerle\.com|jg188\.com|jobcogs\.com|joysflair\.com|joythebaker\.com|judysbook\.com|justagirlblog\.com|kalynskitchen\.com|kbhome\.com|keralahousedesigns\.com|kirschmans\.tv|kitchendaily\.com|kitchens\.com|klonblog\.com|kraftrecipes\.com|kulinarika\.net|lacava\.com|latemag\.com|laurieflower\.com|leroymerlin\.com|liagriffith\.com|lifeinsketch\.com|lifeunplugged\.net|lilacsandlonghorns\.com|liquor\.com|littlecountryvillage\.com|livelovediy\.com|living\.com|livingonadime\.com|livingthecountrylife\.com|loftbuzz\.com|loghomelinks\.com|lonny\.com|lookbook\.nu|loquetuquierasoir\.com|lottieanddoof\.com|louisshanksfurniture\.com|lushome\.com|luxesource\.com|magofyoga\.com|mainehomedesign\.com|majeshome\.com|makingitlovely\.com|manrepeller\.com|marcjacobs\.com|marthastewart\.com|mastercraftinteriors\.com|materialgirlsblog\.com|mbfashionweek\.com|meeganmakes\.com|minimalisti\.com|mlslistings\.com|mobilehomerepair\.com|mocoloco\.com|mohawkflooring\.com|mom4real\.com|momtoob\.com|moneypit\.com|mrfood\.com|mrhandyman\.com|multiline-interiors\.com|myblessedlife\.net|mydownloadplanet\.com|myhomeideas\.com|myparadissi\.com|nari\.org|naturalhandyman\.com|ncpc\.org|networx\.com|nicheinteriors\.com|nigella\.com|ninagarcia\.com|ninjacam\.com|nkba\.org|notcot\.com|nylonmag\.com|offbeathome\.com|oldhousejournal\.com|oldhouseweb\.com|onekindesign\.com|oneprojectcloser\.com|opendownloadmanager\.com|openingstijden\.com|orcrec\.com|organicgardening\.com|organizedhome\.com|ourcountryhaven\.com|ownmodern\.com|padstyle\.com|paintedfurnitureideas\.com|panlasangpinoy\.com|perfectlyimperfectblog\.com|photosofinteriors\.com|pillsbury\.com|pinoyrecipe\.net|pinterest\.com|placesinthehome\.com|plbg\.com|popdizzy\.com|porch\.com|positivelysplendid\.com|prettyhandygirl\.com|programsbase\.com|projectnursery\.com|purseblog\.com|qarchive\.org|quakerrose\.com|rachelray\.com|rainonatinroof\.com|raneytown\.com|ranzom\.com|realsimple\.com|realtor\.com|redcarpet-fashionawards\.com|redfin\.com|reezi\.com|refinedhaystack\.com|remax\.com|remodelista\.com|removeandreplace\.com|renotalk\.com|rent\.com|resourcefurniture\.com|retaildesignblog\.net|retrorenovation\.com|roblox\.com|roche-bobois\.com|romantichomes\.com|ronhazelton\.com|roomido\.com|roomsketcher\.com|roostblog\.com|sanus\.com|sarahsarna\.com|saveur\.com|savvyhomeblog\.com|schewels\.com|searshomeservices\.com|sevenspoons\.net|sew4home\.com|sfgirlbybay\.com|sfgirlbythebay\.com|sfxit\.com|shanty-2-chic\.com|shareme\.com|shegroovez\.com|shelterness\.com|shelterrific\.com|signature9\.com|simplifiedbee\.com|simplygrove\.com|simplyrecipes\.com|skroutzondeck\.com|smallhousebliss\.com|smartcode\.com|smittenkitchen\.com|sneakerskip\.com|snuut\.com|sofotex\.com|soft-ware\.net|softbuzz\.net|softholm\.com|softworld\.com|sohautestyle\.com|soilandhealth\.org|songofstyle\.com|southernliving\.com|styleathome\.com|stylebistro\.com|stylecaster\.com|styledecordeals\.com|styleite\.com|stylelist\.com|stylemepretty\.com|sunset\.com|surfacemag\.com|surya\.com|swafi\.com|sweethome3d\.com|sweetpickinsfurniture\.com|swide\.com|swiss-miss\.com|tagged\.com|tarladalal\.com|tarteletteblog\.com|tazatek\.com|the36thavenue\.com|theblondesalad\.com|thebudgetdecorator\.com|thedesignconfidential\.com|thedesignhome\.com|thedsgnblog\.com|thefashionisto\.com|thefashionspot\.com|theglamourai\.com|theglitterguide\.com|thegloss\.com|thehomestyledirectory\.com|thehousedesigners\.com|thehouseofsmiths\.com|theinspiredroom\.net|theinteriorsaddict\.com|thekimsixfix\.com|thekitchn\.com|theletteredcottage\.net|themattressunderground\.com|themodernhomestead\.com|thenakeddecorator\.com|thenest\.com|thenester\.com|thepioneerwoman\.com|theplancollection\.com|thesartorialist\.com|theshabbycreekcottage\.com|thesmallhousecatalog\.com|thevivant\.com|thinkcontra\.com|thinkingcloset\.com|thisoldhouse\.com|thistlewoodfarms\.com|thistothat\.com|thomasville\.com|thriftyfun\.com|timticks\.com|tinyhousebuild\.com|tinyhousedesign\.com|tinyhouseliving\.com|todayshomeowner\.com|topconsumerreviews\.com|topdreamer\.com|topsecretrecipes\.com|town-n-country-living\.com|traditionalhome\.com|treasuresfurniture\.net|treehouses\.com|trendir\.com|trovi\.com|trulia\.com|unclutterer\.com|uncommondesignsonline\.com|unskinnyboppy\.com|utahrealestate\.com|vanguardfurniture\.com|vegrecipesofindia\.com|velux\.com|veranda\.com|viewpoints\.com|vintagerevivals\.com|visionbedding\.com|vivuz\.com|vizimac\.com|vk\.com|vmagazine\.com|vogue\.com|walkerfurniture\.com|wanelo\.com|wantable\.com|wconline\.com|weather\.com|weburbanist\.com|wegli\.com|wendyslookbook\.com|whatscookingamerica\.net|whfoods\.com|whowhatwear\.com|wikia\.com|williams-sonomainc\.com|winespectator\.com|winportal\.com|wmagazine\.com|wolffurniture\.com|woodfloordoctor\.com|woodmagazine\.com|woodworkersworkshop\.com|world-architects\.com|worthingcourtblog\.com|wreek\.com|x64bitdownload\.com|yankeedownload\.com|yelp\.com|younghouselove\.com|yumsugar\.com|zillow\.com|ziprealty\.com)$/i,
			pageWhiteList: /(homeimprover05|kazan|guttercontract1|hinam-ru)\.livejournal\.com|home\.howstuffworks\.com|(dragonfly|seek)\.autodesk\.com|parknonwoven\.tradeindia\.com|(customtile|wam_mag|jsf1|asiangiftdesigns|awelldressedkitchen|morganimports|pmulberry|fantasyeyeland|homeandgardenshow|homehealer|beautifulliving|abileneinteriordesign|thewoodblock|circleofblessings|associatednaturally)\.homestead\.com|wilber4pail\.mywapblog\.com|cushioncovers8\.webnode\.com|purehomedecor\.jigsy\.com|cushioncovers\.hpage\.com|hometexcollection\.bloombiz\.com|interiordesigners\.buildingpros\.com|furniture\.trendzona\.com|cmorganizing\.citymax\.com|homegarden\.move\.com|homeandgarden\.craftgossip\.com|homebuilding\.thefuntimesguide\.com|lindusconstruction\.areavoices\.com|planete\.qc\.ca\/logiciels|(absolutelybeautifulthings|alainasullivan|alifesdesign|atlantic-pacific|beachbungalow8|call-me-cupcake|joannagoddard|lefashion|songofstyle|thriftydecorchick|whatkatieate)\.blogspot\.com|(answers|homes|news)\.yahoo\.com|yahoo\.com(\/food|\/style)|bbc\.co\.uk(\/food\/recipes|\/food\/recipes)|blog\.haikje\.nl|blog\.mcgrath2\.com|craigslist\.org(\/apa|\/baa|\/fua|\/hsw|\/rea|\/vac)|facebook\.com(\/afhswesttexas|\/Amazon|\/American\.Furniture\.Warehouse|\/AmericanSignatureFurnitureUSA|\/Apt2B|\/ballarddesigns|\/BedBathAndBeyond|\/BHHSRealEstate|\/bludot|\/cb2|\/crateandbarrel|\/CWonderStore|\/CymaxStores|\/DesignWithinReach|\/ethanallendesign|\/gilt|\/hautelook|\/Havertys|\/homedecoratorscollection|\/IKEAUSA|\/jossandmain|\/lazboy|\/LoveSac|\/Nordstrom|\/OneKingsLane|\/Overstock\.com|\/pages\/Bassett-Home-Furnishings\/282306411829612|\/pages\/Jonathan-Adler\/461957923939642|\/pages\/Restoration-Hardware\/108089662552501|\/pier1|\/pinterest|\/potterybarn|\/raymourandflanigan|\/roomstogo|\/scandesignshome|\/serenaandlily|\/target|\/thrivefurniture|\/wayfair|\/WestElm|\/worldmarket|\/YLiving|\/ZGallerie)|(fashion|lifestyle)\.allwomenstalk\.com|forums\.egullet\.org|frontdoor\.com\/real-estate|hes\.lbl\.gov|kountrylife\.com\/index\.htm|mermag\.blogspot\.ca|princessmisia\.com\/blog|realestate\.msn\.com|seaofshoes\.typepad\.com|variety\.com\/v\/dirt/i,
			banners:
			[
				{bannerName: "LTD10", landingPage: "https://itunes.apple.com/us/app/likethat-decor/id906740852", showButtons: false},
				{bannerName: "LTD19", landingPage: "https://itunes.apple.com/us/app/likethat-decor/id906740852", showButtons: false},
				{bannerName: "LTD20", landingPage: "https://itunes.apple.com/us/app/likethat-decor/id906740852", showButtons: false},
				{bannerName: "LTD27", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD27&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD31", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD31&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD39", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD39&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD40", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD40&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD41", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD41&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD42", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD42&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD43", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD43&utm_campaign=LTD_Ads", showButtons: false},
				{bannerName: "LTD44", landingPage: "http://likethatapps.com/?utm_source=WSAdUnit&utm_medium=Banner&utm_content=LTD44&utm_campaign=LTD_Ads", showButtons: false}
			]
		}
	},

	initialize: function()
	{
		this.hostDomain = this.utils.extractDomainName(document.location.host);

		if (!this.initialized)
		{
			this.defineFlow();
		}
	},

	defineFlow: function()
	{
		var flow;

		for (var key in this.flows)
		{
			if (this.flows.hasOwnProperty(key))
			{
				flow = this.flows[key];

				if (this.isDomainWhiteListed(flow.whiteList) || (flow.pageWhiteList && this.isPageWhiteListed(flow.pageWhiteList)) || (flow.terms && this.isProductTermsDetected(flow.terms)))
				{
					this.reportData.prefix = flow.prefix;
					this.startFlow(key);
					break;
				}
			}
		}
	},

    isNeedToShowAd: function ()
    {
        similarproducts.sfdebugger.log('<b>In products:Active units - [' + similarproducts.utilities.getActiveUnits() + ']</b>');
	    if  (
	            similarproducts.utilities.isUnitActive('slideup2') ||  // when not in white list but category 60
	            similarproducts.utilities.isUnitActive('vulcon') ||
	            similarproducts.utilities.isUnitActive('hc') ||
	            similarproducts.utilities.isUnitActive('gameHour')
	        ){
	        similarproducts.sfdebugger.log('<b>No need to show Ads - [' + similarproducts.utilities.getActiveUnits() + '] Showed</b>');
	        return false;
	    } else if(this.reportData.prefix === 'decor' && similarproducts.b.userData.uc !== 'US'){
	        return false;
	    } else if(this.showed){
            return false;
        } else {
            return true;
        }
    },

	isDomainWhiteListed: function(domainRegex)
	{
		return domainRegex.test(this.hostDomain) && this.reportData.siteType.push('wl');
	},

	isPageWhiteListed: function(pageRegex)
	{
		return pageRegex.test(location.href) && this.reportData.siteType.push('wl');
	},

	isProductTermsDetected: function(termsRegex)
	{
		var extractedTerms;

		if (Math.floor(Math.random() * 10) + 1 == 5)
		{
			extractedTerms = this.utils.extractTermsFromTitle(termsRegex) || this.utils.extractTermsFromMetaTags(termsRegex) || this.utils.extractTermsFromH1Tags(termsRegex) || [];

			if (extractedTerms.length)
			{
				this.reportData.foundTerms = extractedTerms;
				this.reportData.siteType.push('pip');

				return true;
			}
		}

		return false;
    },

	startFlow: function(flowKey, type)
	{
        if(!this.reportData.prefix){
            this.reportData.prefix = 'petmatch';  // when not in white list but category 60
        }

	    if  (!this.isNeedToShowAd()){
	        return;
	    }

		var sb = similarproducts.b;
		var serverLayerFrameParams =
		{
			documentTitle: document.title,
			dlSource: sb.dlsource,
			userId: sb.userid,
			ctid: sb.CD_CTID,
			version: sb.appVersion,
			pageUrl: location.href,
			sourceDomain: this.appDomain,
			prefix: this.reportData.prefix
		};

		type && this.reportData.siteType.push(type);

		this.getBannerData(flowKey);

		sb.inj(document, this.appDomain+'css/products.css?v='+sb.appVersion);

		this.$(window).bind("message", this.utils.serverMessagesRouter.bind(this));

		this.serverLayerFrame = this.$('<iframe />',
		{
			style: 'position:absolute; width:0; height:0; left:-100px; top:-100px;',
			src: this.appDomain + 'products/server_layer.html?' + this.utils.compileQueryString(serverLayerFrameParams)+(similarproducts.utilities.abTestUtil && similarproducts.utilities.abTestUtil.getDataString())
		})[0];

		document.body.appendChild(this.serverLayerFrame);

		this.initialized = true;
	},

	processServerData: function(data) // This function is called by the "utils.serverMessagesRouter" func via the "fn" param send by the server layer
	{
	    if  (!this.isNeedToShowAd()){
	        return;
	    }
		this.serverData = data;
        this.showed = true;
        similarproducts.utilities.newUnit('ads_'+this.reportData.prefix);

		similarproducts.Template.initialize(data.template);

		this.render();
		this.defineViewElements();
		this.positionToView();
		this.renderInfo();
		this.activate();
	},

    alreadyShowed: function()
    {
        similarproducts.sfdebugger.log('<b>No need to show Ads - already showed today</b>');
    },

	getBannerData: function(flowKey)
	{
		var banners = this.flows[flowKey].banners;
		var bannerId = Math.floor(Math.random() * banners.length);

		this.reportData.bannerId = bannerId;
		this.banner = banners[bannerId];
	},

	render: function()
	{
		this.$('body').append(similarproducts.Template.render('adsMain',
		{
			creativeImage: this.appDomain + 'products/creatives/' + this.banner.bannerName + ".png",
			showButtons: this.banner.showButtons,
			partnerName: similarproducts.b.qsObj.partnername || similarproducts.b.psuSupportedByText,
		}));

		this.reportAction(
		{
            action: 'ad shown',
            dscr: this.reportData.foundTerms.join('|')
        });
        this.utils.sendMessageToServerLayer.call(this, 'adShowed', this.reportData.prefix);
	},

	defineViewElements: function()
	{
		this.view =
		{
			self: this.$('#similarproducts_ads'),
			unitHeader: this.$('._unit_header', this.view.self),
			closeButton: this.$('._close', this.view.self),
			minimizeButton: this.$('._minimize', this.view.self),
			restoreButton: this.$('._restore', this.view.self),
			banner: this.$('._banner', this.view.self),
			goToStoreButtons: this.$('._go_to_store', this.view.self),
			infoButton: this.$('._show_info', this.view.self),
		};
	},

	activate: function()
	{
		this.view.self.one('mouseover', this.cancelInitialMinimizing.bind(this));

		this.view.banner.click(this.bannerClick.bind(this));
		this.view.goToStoreButtons.click(this.goToStore.bind(this));

		this.view.infoButton.click(this.showInfo.bind(this));
		this.view.closeButton.click(this.closeUnit.bind(this));
		this.view.minimizeButton.click(this.minimizeUnit.bind(this));
		this.view.unitHeader.mouseenter(this.unCloseUnit.bind(this));
		this.view.restoreButton.click(this.restoreUnit.bind(this));
	},

	bannerClick: function()
	{
		this.reportAction(
		{
            action: 'ad clicked',
            target_merchant: this.banner.landingPage
        });

        var url = this.banner.landingPage.indexOf('likethatapps') > -1 ? this.banner.landingPage + '&ws_uid=' + spsupport.p.userid : this.banner.landingPage;
        window.open(url, this.serverData.sessionId);
	},

	goToStore: function(event)
	{
		var button = this.$(event.target);
		var storeName = button.attr('data-store');
		var redirectURL;

		event.stopPropagation();

		switch (storeName)
		{
			case 'itunes':
				redirectURL	= 'https://itunes.apple.com/us/app/petmatch/id857946616';
				break;

			case 'android':
				redirectURL	= 'https://play.google.com/store/apps/details?id=com.superfish.petmatchapp';
				break;

		}

        window.open(redirectURL, this.view.sessionId + '_play');

		this.reportAction(
		{
            action: 'ad clicked',
            target_merchant: redirectURL
        });
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
		var minimizedTimestamp = parseInt(localStorage.getItem('__sfAdsModuleMinimized')) || 0;

		if (this.utils.isTimestampInRange(minimizedTimestamp, 86400000)) // 24hours
		{
			return true;
		}
	},

	isUnitSuperMinimized: function()
	{
		var closeCounter = parseInt(localStorage.getItem('__sfAdsCloseCounter')) || 0;

		if (closeCounter > 1)
		{
			if (this.utils.isTimestampInRange(closeCounter, 21600000)) // 6 hours
			{
				return true;
			}
			else
			{
				localStorage.setItem('__sfAdsCloseCounter', 0);
			}
		}

		/*if (this.utils.isTimestampInRange(this.serverData.closedTimestamp, 86400000)) // 24hours
		{
			return true;
		}*/
	},


	positionToView: function()
	{
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
				unitFinalPosition = -(unitHeight-30);
			}
			else
			{
				this.initialMinimizingTimer = setTimeout(this.autoMinimizeUnit.bind(this), 30000);
			}
		}

		this.view.self.css({bottom:-unitHeight});
		this.view.self.animate({bottom:unitFinalPosition}, 200);
	},

	closeUnit: function()
	{
		var closeCounter = parseInt(localStorage.getItem('__sfAdsCloseCounter')) || 0;

		if (closeCounter == 0)
		{
			localStorage.setItem('__sfAdsCloseCounter', 1);
		}
		else
		{
			localStorage.setItem('__sfAdsCloseCounter', new Date().getTime());
		}

		//this.utils.sendMessageToServerLayer.call(this, 'closeUnit', 'sf_close_ads');
		this.reportAction({action: 'ad hide'});
		this.hideUnit('supermini');
	},

	unCloseUnit: function()
	{
		if (this.minimizedMode == 'supermini')
		{
			localStorage.setItem('__sfAdsCloseCounter', 0);
			//this.utils.sendMessageToServerLayer.call(this, 'unCloseUnit', 'sf_close_ads');
			this.unHideUnit();
		}
	},

	minimizeUnit: function()
	{
		localStorage.setItem('__sfAdsModuleMinimized', new Date().getTime());

		this.hideUnit('mini');
		this.reportAction({action: 'ad minimized'});
	},

	autoMinimizeUnit: function()
	{
		this.hideUnit('mini');
	},

	restoreUnit: function()
	{
		localStorage.removeItem('__sfAdsModuleMinimized');

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

		this.reportAction({action: 'ad restored'});
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

		similarproducts.info.setUnitConfiguration(this.reportData.prefix,
		{
			sessionId: spsupport.p.initialSess,
			showAdditional: true
		});
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

	reportAction: function(data)
	{
		var pixel = new Image();

		data.action = this.reportData.prefix + ' ' + data.action;
		data.userid = spsupport.p.userid;
		data.sessionid = this.serverData.sessionId;
        data.browser = spsupport.api.dtBr();
        data.page_url = window.location.href;
        data.siteType = this.reportData.siteType.join('|');
        data.imageurl = this.banner.bannerName;
        data.source_id = this.reportData.bannerId;
        data.merchantName = spsupport.p.siteDomain;
        data.dlsource = similarproducts.b.dlsource;
        data.country = similarproducts.b.userData.uc;

		pixel.src = this.appDomain + 'trackSession.action?' + this.utils.compileQueryString(data) + similarproducts.utilities.abTestUtil.getDataString();
	}
};


similarproducts.ProductAds.utils =
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

	extractTermsFromTitle: function(termsRegex)
	{
		return document.title.replace(/\s+/gm, ' ').match(termsRegex);
	},

	extractTermsFromMetaTags: function(termsRegex)
	{
		var metaTags = document.getElementsByTagName('meta') || [];
        var tagRegex = /og:title|og:description/i;
		var result;

        for (var i=0, l=metaTags.length; i<l; i++)
        {
            if ((metaTags[i].getAttribute('property') || '').search(tagRegex) !== -1)
            {
                result = (metaTags[i].getAttribute('content') || '').replace(/\s+/gm, ' ').match(termsRegex);

                if (result && result.length)
                {
                    return result;
                }
            }
        }

		return null;
	},

	extractTermsFromH1Tags: function(termsRegex)
	{
		var headers = document.getElementsByTagName('h1') || [];
        var result;

        for (var i=0, l=headers.length; i<l; i++)
        {
            result = headers[i].textContent.replace(/\s+/gm, ' ').match(termsRegex);

            if (result && result.length)
            {
	            return result;
            }
        }

        return null;
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

	isTimestampInRange: function(timestamp, range)
    {
        var now = new Date().getTime();

        return (timestamp + range > now);
    },

	serverMessagesRouter: function(event)
	{
		var data = event.originalEvent.data.split('__similarproductsProductsAdsNamespaceMarker')[1];

		data = data && JSON.parse(data) || null;

		if (data && typeof this[data.fn] === 'function')
		{
			this[data.fn](data.data);
		}
	},

	sendMessageToServerLayer: function(fn, data)
	{
		var targetWindow = this.serverLayerFrame.contentWindow || this.serverLayerFrame;
		var message =
        {
            fn: fn,
            data: data
        };

        targetWindow.postMessage('__similarproductsProductsAdsNamespaceMarker'+JSON.stringify(message), '*');
	}
};

similarproducts.ProductAds.initialize();
