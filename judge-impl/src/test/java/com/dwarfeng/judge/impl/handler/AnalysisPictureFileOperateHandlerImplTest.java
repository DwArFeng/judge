package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.handler.AnalysisPictureFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPictureInfoMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class AnalysisPictureFileOperateHandlerImplTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;
    @Autowired
    private AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService;

    @Autowired
    private FtpHandler ftpHandler;

    @Autowired
    private FtpPathResolver ftpPathResolver;

    @Autowired
    private KeyGenerator<LongIdKey> keyGenerator;

    @Test
    public void test() throws Exception {
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

        LongIdKey longIdKey = keyGenerator.generate();
        try {
            analysisPictureFileOperateHandler.uploadFile(
                    new AnalysisPictureFileUploadInfo(longIdKey, "test-avatar.png", content)
            );

            // 下载图片，头像的内容应该与 content 相等。
            AnalysisPictureFile analysisPictureFileDown = analysisPictureFileOperateHandler.downloadFile(
                    new AnalysisPictureFileDownloadInfo(longIdKey)
            );
            assertArrayEquals(content, analysisPictureFileDown.getContent());

            AnalysisPictureThumbnail analysisPictureFileThumbnailDown =
                    analysisPictureFileOperateHandler.downloadThumbnail(
                            new AnalysisPictureThumbnailDownloadInfo(longIdKey)
                    );
            assertTrue(analysisPictureFileThumbnailDown.getContent().length > 0);

            analysisPictureInfoMaintainService.delete(longIdKey);
            assertFalse(ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE),
                    Long.toString(longIdKey.getLongId())
            ));
            assertFalse(ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL),
                    Long.toString(longIdKey.getLongId())
            ));
        } finally {
            if (Objects.nonNull(longIdKey)) {
                analysisPictureInfoMaintainService.deleteIfExists(longIdKey);
            }
        }
    }
}
