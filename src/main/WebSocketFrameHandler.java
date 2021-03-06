package main;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame>
{
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
	{
		super.userEventTriggered(ctx, evt);
		//noinspection deprecation
		if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE)
		{
			// TODO: Handle the newly connected users
			ctx.channel().closeFuture().addListener(new ChannelFutureListener()
			{
				@Override
				public void operationComplete(ChannelFuture future)
				{
					// TODO: Handle disconnected users
				}
			});
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame)
	{
		if(frame instanceof TextWebSocketFrame)
		{
			// TODO: Handle user text
			ctx.writeAndFlush(new TextWebSocketFrame(((TextWebSocketFrame) frame).text()));
		}
	}
}
