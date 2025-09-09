package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.handler.AnalysisPicturePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AnalysisPicturePackItemFileOperateHandlerImplTest {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;
    @Autowired
    private AnalysisPicturePackMaintainService analysisPicturePackMaintainService;
    @Autowired
    private AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;

    @Autowired
    private FtpHandler ftpHandler;

    @Autowired
    private FtpPathResolver ftpPathResolver;

    private AnalysisPicturePack analysisPicturePack;

    @Before
    public void setUp() {
        analysisPicturePack = new AnalysisPicturePack(null, 0, "remark");
    }

    @After
    public void tearDown() {
        analysisPicturePack = null;
    }

    @Test
    public void test() throws Exception {
        try {
            analysisPicturePack.setKey(analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack));

            // 拿到 test-avatar.png，获取字节数组。
            byte[] content;
            try (
                    InputStream in = ctx.getResource("classpath:ftp/test-avatar.png").getInputStream();
                    ByteArrayOutputStream out = new ByteArrayOutputStream()
            ) {
                IOUtil.trans(in, out, 4096);
                out.flush();
                content = out.toByteArray();
            }
            AnalysisPicturePackItemFileUploadInfo analysisPicturePackItemFileUploadInfo =
                    new AnalysisPicturePackItemFileUploadInfo(analysisPicturePack.getKey(), "test-avatar.png", content);

            LongIdKey longIdKey = analysisPicturePackItemFileOperateHandler.uploadFile(
                    analysisPicturePackItemFileUploadInfo
            );
            // 下载图片，图片的内容应该与 content 相等。
            AnalysisPicturePackItemFile analysisPicturePackFile =
                    analysisPicturePackItemFileOperateHandler.downloadFile(
                            new AnalysisPicturePackItemFileDownloadInfo(longIdKey)
                    );
            assertArrayEquals(content, analysisPicturePackFile.getContent());

            AnalysisPicturePackItemThumbnail analysisPicturePackItemThumbnail =
                    analysisPicturePackItemFileOperateHandler.downloadThumbnail(
                            new AnalysisPicturePackItemThumbnailDownloadInfo(longIdKey)
                    );
            assertTrue(analysisPicturePackItemThumbnail.getContent().length > 0);

            analysisPicturePackItemInfoMaintainService.delete(longIdKey);
            assertFalse(ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE),
                    Long.toString(longIdKey.getLongId())
            ));
            assertFalse(ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL),
                    Long.toString(longIdKey.getLongId())
            ));
        } finally {
            analysisPicturePackMaintainService.deleteIfExists(analysisPicturePack.getKey());
        }
    }
}
