var animationFrame;

function frame() {
  animationFrame = requestAnimationFrame(frame);

  // Actually draw

  // Let the host app know
  window.androidWallpaperInterface.drawFrame();
}

function pauseWallpaper() {
  cancelAnimationFrame(animationFrame);
}

function resumeWallpaper() {
  frame();
}

frame();