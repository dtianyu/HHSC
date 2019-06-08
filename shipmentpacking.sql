/*
-- Query: SELECT * FROM hhsc.sysprg where id=109
LIMIT 0, 1000

-- Date: 2019-01-18 21:27
*/
INSERT INTO `sysprg` (`id`,`name`,`api`,`moduleid`,`sortid`,`noauto`,`nochange`,`nolead`,`noformat`,`noseqlen`,`doadd`,`doedit`,`dodel`,`doprt`,`dopriv`,`docfm`,`douncfm`,`doaudit`,`dounaudit`,`rptclazz`,`rptdesign`,`rptjndi`,`rptformat`,`descript`,`status`,`creator`,`credate`,`optuser`,`optdate`,`cfmuser`,`cfmdate`) VALUES (109,'出货装箱','shipmentpacking',1,51,1,0,'SP','yyMM',4,1,1,1,1,0,1,1,0,0,'com.hhsc.rpt.ShipmentPackingReport','shipmentpacking.rptdesign','java:global/HHSC/HHSC-ejb/ShipmentPackingBean!com.hhsc.ejb.ShipmentPackingBean','pdf','','N','系统管理员','2019-01-16 22:35:40',NULL,NULL,NULL,NULL);
