package com.mldong.log;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.servlet.ServletUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 日志-请求包装类
 */
public class LogRequestWrapper extends HttpServletRequestWrapper {
    private String body;
    private boolean flag = false;
    public LogRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if(!ServletUtil.isMultipart(request)) {
            this.body = IoUtil.readUtf8(request.getInputStream());
            this.flag = true;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if(this.flag) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes(Charset.defaultCharset()));
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }
            };
        }
        return super.getInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return body;
    }
}
