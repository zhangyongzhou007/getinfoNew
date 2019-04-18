package com.QuestionBank.dao.mongo;

import com.QuestionBank.entity.LiveInfoEntity;
import com.QuestionBank.utils.StringUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.star.database.mongo.WebAbstractProvider;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 单直播访客量统计
 * @ClassName: LiveStatisUserProvider 
 * @Description: 
 * @author jiangchunlin
 * @date 2018-3-2 下午4:42:28
 */
public class LiveStatisProvider extends WebAbstractProvider {

	private Logger logger = LoggerFactory.getLogger(LiveStatisProvider.class);

	private static final String tableName = "t_live_statis_all";

	private static LiveStatisProvider instance = new LiveStatisProvider();

	private MongoCollection<Document> mongoCollection;

	public LiveStatisProvider() {
		super();
		mongoCollection = this.getMongoDB().getCollection(tableName);
	}
	
	public static LiveStatisProvider getInstance() {
		return instance;
	}
	
	@Override
	protected String getCollectionName()
	{
		return tableName;
	}

	@Override
	protected Map<Document, IndexOptions> getIndex()
	{
		Map<Document, IndexOptions> index = new HashMap<Document, IndexOptions>();
		index.put(new Document(LiveInfoEntity.LIVE_ID, 1), new IndexOptions());
		return index;
	}
	


	/**
	 * @Author ZhangFuGui
	 * @Description 
	 * @Date 10:43 2019/4/8 0008
	 * @Param [liveInfoEntity]
	 * @return void
	 */
	public LiveInfoEntity getLiveInfoList(LiveInfoEntity liveInfoEntity) {
		Document filter=new Document();
		if (!StringUtils.isBlank(liveInfoEntity.getSubjectId())){
			filter.append(LiveInfoEntity.LIVE_ID,liveInfoEntity.getSubjectId());
		}
		logger.info("filter:"+filter);
		MongoCursor<Document> iterator = mongoCollection.find(filter).limit(1).iterator();
		while (iterator.hasNext()){
			Document next = iterator.next();
			liveInfoEntity.setVisitTimes((next.get(LiveInfoEntity.VISIT_TIMES)==null?0:next.get(LiveInfoEntity.VISIT_TIMES))+"");
			liveInfoEntity.setPlayTimes((next.get(LiveInfoEntity.PLAY_TIMES)==null?0:next.get(LiveInfoEntity.PLAY_TIMES))+"");
			liveInfoEntity.setReVisitTimes((next.get(LiveInfoEntity.REVISIT_TIMES)==null?0:next.get(LiveInfoEntity.REVISIT_TIMES))+"");

			logger.info("UserEntity:"+next);

		}
		System.out.println(liveInfoEntity.toString()+":liveInfoEntity");
		return liveInfoEntity;
	}
}
